package dev.havlicektomas.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.havlicektomas.core.data.database.dao.TransactionDao
import dev.havlicektomas.core.data.database.entity.TransactionEntity

@Database(
    entities = [TransactionEntity::class],
    version = 1
)
abstract class FinanceDatabase: RoomDatabase() {

    abstract val transactionDao: TransactionDao
}