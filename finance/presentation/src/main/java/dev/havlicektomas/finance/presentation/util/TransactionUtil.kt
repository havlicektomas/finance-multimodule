package dev.havlicektomas.finance.presentation.util

import dev.havlicektomas.core.domain.finance.FinanceTransactionCategory

fun getTransactionCategories(): List<String> {
    return FinanceTransactionCategory.entries.map { entry ->
        entry.name.lowercase()
    }
}

fun getTransactionCategory(categoryString: String): FinanceTransactionCategory {
    return when(categoryString) {
        "clothing" -> FinanceTransactionCategory.CLOTHING
        "education" -> FinanceTransactionCategory.EDUCATION
        "entertainment" -> FinanceTransactionCategory.ENTERTAINMENT
        "food" -> FinanceTransactionCategory.FOOD
        "health" -> FinanceTransactionCategory.HEALTH
        else -> FinanceTransactionCategory.OTHER
    }
}
