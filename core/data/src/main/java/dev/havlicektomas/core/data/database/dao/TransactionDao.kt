package dev.havlicektomas.core.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.havlicektomas.core.data.database.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Upsert
    suspend fun upsertTransaction(transaction: TransactionEntity)

    @Upsert
    suspend fun upsertTransactions(transactions: List<TransactionEntity>)

    @Query("DELETE FROM TransactionEntity WHERE id=:id")
    suspend fun deleteTransaction(id: String)

    @Query("DELETE FROM TransactionEntity")
    suspend fun deleteAllTransactions()

    @Query("SELECT * FROM TransactionEntity ORDER BY timestamp DESC")
    fun getTransactions(): Flow<List<TransactionEntity>>
}