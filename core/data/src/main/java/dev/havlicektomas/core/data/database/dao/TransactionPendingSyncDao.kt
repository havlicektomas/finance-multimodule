package dev.havlicektomas.core.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.havlicektomas.core.data.database.entity.DeletedTransactionSyncEntity
import dev.havlicektomas.core.data.database.entity.TransactionPendingSyncEntity

@Dao
interface TransactionPendingSyncDao {

    // CREATED TRANSACTIONS
    @Query("SELECT * FROM TransactionPendingSyncEntity WHERE userId=:userId")
    suspend fun getAllTransactionPendingSyncEntities(userId: String): List<TransactionPendingSyncEntity>

    @Query("SELECT * FROM TransactionPendingSyncEntity WHERE transactionId=:transactionId")
    suspend fun getTransactionPendingSyncEntity(transactionId: String): TransactionPendingSyncEntity?

    @Upsert
    suspend fun upsertTransactionPendingSyncEntity(entity: TransactionPendingSyncEntity)

    @Query("DELETE FROM TransactionPendingSyncEntity WHERE transactionId=:transactionId")
    suspend fun deleteTransactionPendingSyncEntity(transactionId: String)


    // DELETED TRANSACTIONS
    @Query("SELECT * FROM DeletedTransactionSyncEntity WHERE userId=:userId")
    suspend fun getAllDeletedTransactionSyncEntities(userId: String): List<DeletedTransactionSyncEntity>

    @Upsert
    suspend fun upsertDeletedTransactionSyncEntity(entity: DeletedTransactionSyncEntity)

    @Query("DELETE FROM DeletedTransactionSyncEntity WHERE transactionId=:transactionId")
    suspend fun deleteDeletedTransactionSyncEntity(transactionId: String)
}