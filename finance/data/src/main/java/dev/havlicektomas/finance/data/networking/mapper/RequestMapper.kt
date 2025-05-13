package dev.havlicektomas.finance.data.networking.mapper

import dev.havlicektomas.core.domain.finance.FinanceTransaction
import dev.havlicektomas.finance.data.networking.CreateTransactionRequest
import java.util.UUID

fun FinanceTransaction.toCreateRequest(): CreateTransactionRequest {
    return CreateTransactionRequest(
        id = id!!,
        type = type.name,
        title = title,
        amount = amount,
        note = note,
        category = category.name,
        epochMillis = timestamp.toEpochSecond() * 1000L
    )
}