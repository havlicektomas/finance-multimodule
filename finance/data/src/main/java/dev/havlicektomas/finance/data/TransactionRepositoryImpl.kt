package dev.havlicektomas.finance.data

import dev.havlicektomas.core.domain.finance.FinanceTransaction
import dev.havlicektomas.core.domain.util.DataError
import dev.havlicektomas.core.domain.util.EmptyResult
import dev.havlicektomas.core.domain.util.Result
import dev.havlicektomas.core.domain.util.asEmptyDataResult
import dev.havlicektomas.finance.domain.TransactionRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds

class TransactionRepositoryImpl(): TransactionRepository {

    private val transactions = mutableListOf<FinanceTransaction>()

    override suspend fun createTransaction(transaction: FinanceTransaction): EmptyResult<DataError.Network> {
        delay(1.seconds)
        transactions.add(transaction)
        return Result.Success(Unit).asEmptyDataResult()
    }

    override suspend fun getTransactions(): Result<List<FinanceTransaction>, DataError.Network> {
        return Result.Success(transactions.toList())
    }

    override fun transactionsFlow(): Flow<List<FinanceTransaction>> {
        return flow {
            emit(transactions.toList())
        }
    }
}