package dev.havlicektomas.core.domain.finance

import kotlin.time.Duration

interface SyncTransactionScheduler {

    suspend fun scheduleSync(type: SyncType)
    suspend fun cancelAllSyncs()

    sealed interface SyncType {
        data class FetchTransactions(val interval: Duration): SyncType
        data class DeleteTransaction(val transactionId: FinanceTransactionId): SyncType
        class CreateTransaction(val transaction: FinanceTransaction): SyncType
    }
}