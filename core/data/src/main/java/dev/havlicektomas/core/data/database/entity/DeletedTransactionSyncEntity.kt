package dev.havlicektomas.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeletedTransactionSyncEntity(
    @PrimaryKey(autoGenerate = false)
    val transactionId: String,
    val userId: String
)
