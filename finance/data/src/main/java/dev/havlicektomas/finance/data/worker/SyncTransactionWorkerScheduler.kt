package dev.havlicektomas.finance.data.worker

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.await
import dev.havlicektomas.core.data.database.dao.TransactionPendingSyncDao
import dev.havlicektomas.core.data.database.entity.DeletedTransactionSyncEntity
import dev.havlicektomas.core.data.database.entity.TransactionPendingSyncEntity
import dev.havlicektomas.core.data.database.mapper.toTransactionEntity
import dev.havlicektomas.core.domain.auth.SessionStorage
import dev.havlicektomas.core.domain.finance.FinanceTransaction
import dev.havlicektomas.core.domain.finance.FinanceTransactionId
import dev.havlicektomas.core.domain.finance.SyncTransactionScheduler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.toJavaDuration

class SyncTransactionWorkerScheduler(
    private val context: Context,
    private val pendingSyncDao: TransactionPendingSyncDao,
    private val sessionStorage: SessionStorage,
    private val applicationScope: CoroutineScope
): SyncTransactionScheduler {

    private val workManager = WorkManager.getInstance(context)

    override suspend fun scheduleSync(type: SyncTransactionScheduler.SyncType) {
        when(type) {
            is SyncTransactionScheduler.SyncType.FetchTransactions -> scheduleFetchTransactionsWorker(type.interval)
            is SyncTransactionScheduler.SyncType.DeleteTransaction -> scheduleDeleteTransactionWorker(type.transactionId)
            is SyncTransactionScheduler.SyncType.CreateTransaction -> scheduleCreateTransactionWorker(transaction = type.transaction)
        }
    }

    private suspend fun scheduleDeleteTransactionWorker(transactionId: FinanceTransactionId) {
        val userId = sessionStorage.get()?.userId ?: return
        val entity = DeletedTransactionSyncEntity(
            transactionId = transactionId,
            userId = userId
        )
        pendingSyncDao.upsertDeletedTransactionSyncEntity(entity)

        val workRequest = OneTimeWorkRequestBuilder<DeleteTransactionWorker>()
            .addTag("delete_work")
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.EXPONENTIAL,
                backoffDelay = 2000L,
                timeUnit = TimeUnit.MILLISECONDS
            )
            .setInputData(
                Data.Builder()
                    .putString(DeleteTransactionWorker.TRANSACTION_ID, entity.transactionId)
                    .build()
            )
            .build()

        applicationScope.launch {
            workManager.enqueue(workRequest).await()
        }.join()
    }

    private suspend fun scheduleCreateTransactionWorker(transaction: FinanceTransaction) {
        val userId = sessionStorage.get()?.userId ?: return
        val pendingTransaction = TransactionPendingSyncEntity(
            transaction = transaction.toTransactionEntity(),
            userId = userId
        )
        pendingSyncDao.upsertTransactionPendingSyncEntity(pendingTransaction)

        val workRequest = OneTimeWorkRequestBuilder<CreateTransactionWorker>()
            .addTag("create_work")
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.EXPONENTIAL,
                backoffDelay = 2000L,
                timeUnit = TimeUnit.MILLISECONDS
            )
            .setInputData(
                Data.Builder()
                    .putString(CreateTransactionWorker.TRANSACTION_ID, pendingTransaction.transactionId)
                    .build()
            )
            .build()

        applicationScope.launch {
            workManager.enqueue(workRequest).await()
        }.join()
    }

    private suspend fun scheduleFetchTransactionsWorker(interval: Duration) {
        val isSyncScheduled = withContext(Dispatchers.IO) {
            workManager
                .getWorkInfosByTag("sync_work")
                .get()
                .isNotEmpty()
        }
        if(isSyncScheduled) {
            return
        }

        val workRequest = PeriodicWorkRequestBuilder<FetchTransactionsWorker>(
            repeatInterval = interval.toJavaDuration()
        )
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.EXPONENTIAL,
                backoffDelay = 2000L,
                timeUnit = TimeUnit.MILLISECONDS
            )
            .setInitialDelay(
                duration = 30,
                timeUnit = TimeUnit.MINUTES
            )
            .addTag("sync_work")
            .build()

        workManager.enqueue(workRequest).await()
    }

    override suspend fun cancelAllSyncs() {
        WorkManager.getInstance(context)
            .cancelAllWork()
            .await()
    }
}