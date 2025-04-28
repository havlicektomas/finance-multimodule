package dev.havlicektomas.finance.presentation.model

import dev.havlicektomas.core.domain.finance.FinanceTransactionCategory
import dev.havlicektomas.core.domain.finance.FinanceTransactionType

data class UITransaction(
    val id: String? = null,
    val type: FinanceTransactionType = FinanceTransactionType.EXPENSE,
    val title: String = "",
    val amount: String = "",
    val note: String = "",
    val category: FinanceTransactionCategory = FinanceTransactionCategory.OTHER,
    val timestamp: String = ""
)
