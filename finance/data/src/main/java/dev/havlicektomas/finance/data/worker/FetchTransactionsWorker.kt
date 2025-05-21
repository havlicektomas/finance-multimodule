package dev.havlicektomas.finance.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.havlicektomas.core.domain.finance.TransactionRepository

class FetchTransactionsWorker(
    context: Context,
    params: WorkerParameters,
    private val transactionRepository: TransactionRepository
): CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        if (runAttemptCount >= 5) {
            return Result.failure()
        }
        return when(val result = transactionRepository.fetchTransactions()) {
            is dev.havlicektomas.core.domain.util.Result.Error -> {
                result.error.toWorkerResult()
            }
            is dev.havlicektomas.core.domain.util.Result.Success -> Result.success()
        }
    }
}