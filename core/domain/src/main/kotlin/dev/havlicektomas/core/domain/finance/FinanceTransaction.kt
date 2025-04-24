package dev.havlicektomas.core.domain.finance

import java.time.ZonedDateTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class FinanceTransaction(
    val id: String?,
    val type: FinanceTransactionType,
    val title: String,
    val amount: Double,
    val note: String,
    val category: FinanceTransactionCategory,
    val timestamp: ZonedDateTime
)

enum class FinanceTransactionType {
    EXPENSE,
    INCOME
}

enum class FinanceTransactionCategory {
    CLOTHING,
    EDUCATION,
    ENTERTAINMENT,
    FOOD,
    HEALTH,
    OTHER
}
