package dev.havlicektomas.core.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class TransactionDto(
    val id: String,
    val type: String,
    val title: String,
    val amount: Double,
    val note: String,
    val category: String,
    val timestamp: String
)
