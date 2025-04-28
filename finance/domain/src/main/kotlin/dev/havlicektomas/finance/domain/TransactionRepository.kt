package dev.havlicektomas.finance.domain

import dev.havlicektomas.core.domain.finance.FinanceTransaction
import dev.havlicektomas.core.domain.util.DataError
import dev.havlicektomas.core.domain.util.EmptyResult
import dev.havlicektomas.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun createTransaction(transaction: FinanceTransaction): EmptyResult<DataError.Network>
    suspend fun getTransactions(): Result<List<FinanceTransaction>, DataError.Network>
    fun transactionsFlow(): Flow<List<FinanceTransaction>>
}