package dev.havlicektomas.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.havlicektomas.core.data.database.dao.TransactionDao
import dev.havlicektomas.core.data.database.dao.TransactionPendingSyncDao
import dev.havlicektomas.core.data.database.entity.DeletedTransactionSyncEntity
import dev.havlicektomas.core.data.database.entity.TransactionEntity
import dev.havlicektomas.core.data.database.entity.TransactionPendingSyncEntity

@Database(
    entities = [
        TransactionEntity::class,
        TransactionPendingSyncEntity::class,
        DeletedTransactionSyncEntity::class
    ],
    version = 1
)
abstract class FinanceDatabase: RoomDatabase() {

    abstract val transactionDao: TransactionDao
    abstract val transactionPendingSyncDao: TransactionPendingSyncDao
}