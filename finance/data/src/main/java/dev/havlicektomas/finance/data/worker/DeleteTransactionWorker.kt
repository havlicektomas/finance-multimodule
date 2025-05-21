package dev.havlicektomas.finance.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.havlicektomas.core.data.database.dao.TransactionPendingSyncDao
import dev.havlicektomas.core.domain.finance.RemoteTransactionDataSource

class DeleteTransactionWorker(
    context: Context,
    private val params: WorkerParameters,
    private val remoteDataSource: RemoteTransactionDataSource,
    private val pendingSyncDao: TransactionPendingSyncDao
): CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        if(runAttemptCount >= 5) {
            return Result.failure()
        }

        val transactionId = params.inputData.getString(TRANSACTION_ID) ?: return Result.failure()
        return when(val result = remoteDataSource.deleteTransaction(transactionId)) {
            is dev.havlicektomas.core.domain.util.Result.Error -> {
                result.error.toWorkerResult()
            }
            is dev.havlicektomas.core.domain.util.Result.Success -> {
                pendingSyncDao.deleteDeletedTransactionSyncEntity(transactionId)
                Result.success()
            }
        }
    }

    companion object {
        const val TRANSACTION_ID = "TRANSACTION_ID"
    }
}