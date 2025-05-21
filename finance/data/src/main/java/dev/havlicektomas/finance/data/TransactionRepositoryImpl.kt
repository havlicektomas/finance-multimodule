package dev.havlicektomas.finance.data

import dev.havlicektomas.core.data.database.dao.TransactionPendingSyncDao
import dev.havlicektomas.core.data.database.mapper.toTransaction
import dev.havlicektomas.core.domain.auth.SessionStorage
import dev.havlicektomas.core.domain.finance.FinanceTransaction
import dev.havlicektomas.core.domain.finance.FinanceTransactionId
import dev.havlicektomas.core.domain.finance.LocalTransactionDataSource
import dev.havlicektomas.core.domain.finance.RemoteTransactionDataSource
import dev.havlicektomas.core.domain.finance.SyncTransactionScheduler
import dev.havlicektomas.core.domain.finance.TransactionRepository
import dev.havlicektomas.core.domain.util.DataError
import dev.havlicektomas.core.domain.util.EmptyResult
import dev.havlicektomas.core.domain.util.Result
import dev.havlicektomas.core.domain.util.asEmptyDataResult
import dev.havlicektomas.finance.data.worker.SyncTransactionWorkerScheduler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionRepositoryImpl(
    private val localTransactionDataSource: LocalTransactionDataSource,
    private val remoteTransactionDataSource: RemoteTransactionDataSource,
    private val applicationScope: CoroutineScope,
    private val sessionStorage: SessionStorage,
    private val transactionPendingSyncDao: TransactionPendingSyncDao,
    private val syncTransactionScheduler: SyncTransactionScheduler
): TransactionRepository {

    override fun transactionsFlow(): Flow<List<FinanceTransaction>> {
        return localTransactionDataSource.getTransactions()
    }

    override suspend fun fetchTransactions(): EmptyResult<DataError> {
        return when(val result = remoteTransactionDataSource.getTransactions()) {
            is Result.Error -> result.asEmptyDataResult()
            is Result.Success -> {
                applicationScope.async {
                    localTransactionDataSource.upsertTransactions(result.data).asEmptyDataResult()
                }.await()
            }
        }
    }

    override suspend fun upsertTransaction(transaction: FinanceTransaction): EmptyResult<DataError> {
        val localResult = localTransactionDataSource.upsertTransaction(transaction)
        if(localResult !is Result.Success) {
            return localResult.asEmptyDataResult()
        }

        val transactionWithId = transaction.copy(id = localResult.data)
        val remoteResult = remoteTransactionDataSource.postTransaction(
            transaction = transactionWithId
        )

        return when(remoteResult) {
            is Result.Error -> {
                applicationScope.launch {
                    syncTransactionScheduler.scheduleSync(
                        type = SyncTransactionScheduler.SyncType.CreateTransaction(
                            transaction = transactionWithId
                        )
                    )
                }.join()
                Result.Success(Unit)
            }
            is Result.Success -> {
                applicationScope.async {
                    localTransactionDataSource.upsertTransaction(remoteResult.data).asEmptyDataResult()
                }.await()
            }
        }

    }

    override suspend fun deleteRunTransactions(id: FinanceTransactionId) {
        localTransactionDataSource.deleteTransaction(id)

        // Edge case where the run is created in offline-mode,
        // and then deleted in offline-mode as well. In that case,
        // we don't need to sync anything.
        val isPendingSync = transactionPendingSyncDao.getTransactionPendingSyncEntity(id) != null
        if(isPendingSync) {
            transactionPendingSyncDao.deleteTransactionPendingSyncEntity(id)
            return
        }

        val remoteResult = applicationScope.async {
            remoteTransactionDataSource.deleteTransaction(id)
        }.await()

        if(remoteResult is Result.Error) {
            applicationScope.launch {
                syncTransactionScheduler.scheduleSync(
                    type = SyncTransactionScheduler.SyncType.DeleteTransaction(id)
                )
            }.join()
        }
    }

    override suspend fun syncPendingTransactions() {
        withContext(Dispatchers.IO) {
            val userId = sessionStorage.get()?.userId ?: return@withContext

            val createdTransactions = async {
                transactionPendingSyncDao.getAllTransactionPendingSyncEntities(userId)
            }

            val deletedTransactions = async {
                transactionPendingSyncDao.getAllDeletedTransactionSyncEntities(userId)
            }

            val createJobs = createdTransactions
                .await()
                .map {
                    launch {
                        val transaction = it.transaction.toTransaction()
                        when(remoteTransactionDataSource.postTransaction(transaction)) {
                            is Result.Error -> Unit
                            is Result.Success -> {
                                applicationScope.launch {
                                    transactionPendingSyncDao.deleteTransactionPendingSyncEntity(it.transactionId)
                                }.join()
                            }
                        }
                    }
                }

            val deleteJobs = deletedTransactions
                .await()
                .map {
                    launch {
                        when(remoteTransactionDataSource.deleteTransaction(it.transactionId)) {
                            is Result.Error -> Unit
                            is Result.Success -> {
                                applicationScope.launch {
                                    transactionPendingSyncDao.deleteDeletedTransactionSyncEntity(it.transactionId)
                                }.join()
                            }
                        }
                    }
                }

            createJobs.forEach { it.join() }
            deleteJobs.forEach { it.join() }
        }
    }
}