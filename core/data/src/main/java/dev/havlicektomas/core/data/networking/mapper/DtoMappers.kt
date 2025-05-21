package dev.havlicektomas.core.data.networking.mapper

import dev.havlicektomas.core.data.networking.dto.TransactionDto
import dev.havlicektomas.core.domain.finance.FinanceTransaction
import dev.havlicektomas.core.domain.finance.FinanceTransactionCategory
import dev.havlicektomas.core.domain.finance.FinanceTransactionType
import java.time.Instant
import java.time.ZoneId

fun TransactionDto.toTransaction(): FinanceTransaction {
    return FinanceTransaction(
        id = id,
        type = FinanceTransactionType.valueOf(type),
        title = title,
        amount = amount,
        note = note,
        category = FinanceTransactionCategory.valueOf(category),
        timestamp = Instant.parse(timestamp)
            .atZone(ZoneId.of("UTC"))
    )
}