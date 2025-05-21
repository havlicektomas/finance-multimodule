package dev.havlicektomas.core.domain.finance

import dev.havlicektomas.core.domain.util.DataError
import dev.havlicektomas.core.domain.util.EmptyResult
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun transactionsFlow(): Flow<List<FinanceTransaction>>
    suspend fun fetchTransactions(): EmptyResult<DataError>
    suspend fun upsertTransaction(transaction: FinanceTransaction): EmptyResult<DataError>
    suspend fun deleteTransaction(id: FinanceTransactionId)
    suspend fun syncPendingTransactions()
    suspend fun logout(): EmptyResult<DataError>
}