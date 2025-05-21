package dev.havlicektomas.finance.data.networking

import kotlinx.serialization.Serializable

@Serializable
data class CreateTransactionRequest(
    val id: String,
    val type: String,
    val title: String,
    val amount: Double,
    val note: String,
    val category: String,
    val epochMillis: Long
)
