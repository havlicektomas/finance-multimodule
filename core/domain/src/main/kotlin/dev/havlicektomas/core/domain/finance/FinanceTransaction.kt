package dev.havlicektomas.core.domain.finance

import java.time.ZonedDateTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class FinanceTransaction(
    val id: Uuid = Uuid.random(),
    val type: FinanceTransactionType = FinanceTransactionType.EXPENSE,
    val title: String = "",
    val amount: Double = 0.0,
    val note: String = "",
    val category: FinanceTransactionCategory = FinanceTransactionCategory.OTHER,
    val timestamp: ZonedDateTime = ZonedDateTime.now()
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
