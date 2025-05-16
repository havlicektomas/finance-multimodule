package dev.havlicektomas.finance.data

import dev.havlicektomas.core.domain.finance.FinanceTransaction
import dev.havlicektomas.core.domain.finance.FinanceTransactionId
import dev.havlicektomas.core.domain.finance.LocalTransactionDataSource
import dev.havlicektomas.core.domain.finance.RemoteTransactionDataSource
import dev.havlicektomas.core.domain.finance.TransactionRepository
import dev.havlicektomas.core.domain.util.DataError
import dev.havlicektomas.core.domain.util.EmptyResult
import dev.havlicektomas.core.domain.util.Result
import dev.havlicektomas.core.domain.util.asEmptyDataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(
    private val localTransactionDataSource: LocalTransactionDataSource,
    private val remoteTransactionDataSource: RemoteTransactionDataSource,
    private val applicationScope: CoroutineScope
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

        val remoteResult = applicationScope.async {
            remoteTransactionDataSource.deleteTransaction(id)
        }.await()
    }
}