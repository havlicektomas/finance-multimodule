package dev.havlicektomas.core.data.database

import android.database.sqlite.SQLiteFullException
import dev.havlicektomas.core.data.database.dao.TransactionDao
import dev.havlicektomas.core.data.database.mapper.toTransaction
import dev.havlicektomas.core.data.database.mapper.toTransactionEntity
import dev.havlicektomas.core.domain.finance.FinanceTransaction
import dev.havlicektomas.core.domain.finance.FinanceTransactionId
import dev.havlicektomas.core.domain.finance.LocalTransactionDataSource
import dev.havlicektomas.core.domain.util.DataError
import dev.havlicektomas.core.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomLocalTransactionDataSource(
    private val transactionDao: TransactionDao
): LocalTransactionDataSource {

    override fun getTransactions(): Flow<List<FinanceTransaction>> {
        return transactionDao.getTransactions()
            .map { entities ->
                entities.map { it.toTransaction() }
            }
    }

    override suspend fun upsertTransaction(transaction: FinanceTransaction): Result<FinanceTransactionId, DataError.Local> {
        return try {
            val entity = transaction.toTransactionEntity()
            transactionDao.upsertTransaction(entity)
            Result.Success(entity.id)
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun upsertTransactions(transactions: List<FinanceTransaction>): Result<List<FinanceTransactionId>, DataError.Local> {
        return try {
            val entities = transactions.map { it.toTransactionEntity() }
            transactionDao.upsertTransactions(entities)
            Result.Success(entities.map { it.id })
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteTransaction(id: String) {
        transactionDao.deleteTransaction(id)
    }

    override suspend fun deleteAllTransactions() {
        transactionDao.deleteAllTransactions()
    }
}