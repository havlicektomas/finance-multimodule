package dev.havlicektomas.core.domain.finance

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class FinanceTransaction @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid = Uuid.random(),
    val type: FinanceTransactionType = FinanceTransactionType.EXPENSE,
    val title: String = "",
    val amount: Double = 0.0,
    val note: String = "",
    val category: FinanceTransactionCategory = FinanceTransactionCategory.OTHER,
    val timestamp: String = "",
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
