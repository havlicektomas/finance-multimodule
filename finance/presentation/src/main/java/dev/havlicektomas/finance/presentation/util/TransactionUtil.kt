package dev.havlicektomas.finance.presentation.util

import dev.havlicektomas.core.domain.finance.FinanceTransactionCategory
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun getTransactionCategories(): List<String> {
    val categoryList = mutableListOf<String>()
    for (category in FinanceTransactionCategory.entries) {
        category.name.lowercase()
    }
    return categoryList.toList()
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
