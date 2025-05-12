package dev.havlicektomas.core.domain.finance

import dev.havlicektomas.core.domain.util.DataError
import dev.havlicektomas.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

typealias FinanceTransactionId = String

interface LocalTransactionDataSource {
    fun getTransactions(): Flow<List<FinanceTransaction>>
    suspend fun upsertTransaction(transaction: FinanceTransaction): Result<FinanceTransactionId, DataError.Local>
    suspend fun upsertTransactions(transactions: List<FinanceTransaction>): Result<List<FinanceTransactionId>, DataError.Local>
    suspend fun deleteTransaction(id: String)
    suspend fun deleteAllTransactions()
}