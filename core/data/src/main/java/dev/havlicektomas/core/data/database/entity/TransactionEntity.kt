package dev.havlicektomas.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val type: String,
    val title: String,
    val amount: Double,
    val note: String,
    val category: String,
    val timestamp: String
)
