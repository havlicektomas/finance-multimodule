package dev.havlicektomas.core.domain.finance

import dev.havlicektomas.core.domain.util.DataError
import dev.havlicektomas.core.domain.util.EmptyResult
import dev.havlicektomas.core.domain.util.Result

interface RemoteTransactionDataSource {
    suspend fun getTransactions(): Result<List<FinanceTransaction>, DataError.Network>
    suspend fun postTransaction(transaction: FinanceTransaction): Result<FinanceTransaction, DataError.Network>
    suspend fun deleteTransaction(id: String): EmptyResult<DataError.Network>
}