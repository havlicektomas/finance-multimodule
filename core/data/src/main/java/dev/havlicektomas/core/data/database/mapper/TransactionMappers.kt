package dev.havlicektomas.core.data.database.mapper

import dev.havlicektomas.core.data.database.entity.TransactionEntity
import dev.havlicektomas.core.domain.finance.FinanceTransaction
import dev.havlicektomas.core.domain.finance.FinanceTransactionCategory
import dev.havlicektomas.core.domain.finance.FinanceTransactionType
import java.time.Instant
import java.time.ZoneId
import java.util.UUID

fun FinanceTransaction.toTransactionEntity(): TransactionEntity {
    return TransactionEntity(
        id = id ?: UUID.randomUUID().toString(),
        type = type.name,
        title = title,
        amount = amount,
        note = note,
        category = category.name,
        timestamp = timestamp.toInstant().toString()
    )
}

fun TransactionEntity.toTransaction(): FinanceTransaction {
    return FinanceTransaction(
        id = id,
        type = FinanceTransactionType.valueOf(type),
        title = title,
        amount = amount,
        note = title,
        category = FinanceTransactionCategory.valueOf(category),
        timestamp = Instant.parse(timestamp).atZone(ZoneId.of("UTC"))
    )
}