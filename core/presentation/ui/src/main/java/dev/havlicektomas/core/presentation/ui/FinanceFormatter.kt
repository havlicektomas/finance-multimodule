package dev.havlicektomas.core.presentation.ui

import dev.havlicektomas.core.domain.finance.FinanceTransactionType

fun amountFormatter(
    amount: Double,
    currency: String,
    transactionType: FinanceTransactionType = FinanceTransactionType.INCOME
): String {
    val operator = if(transactionType == FinanceTransactionType.EXPENSE) "-" else ""
    val amountString = amount.toString()
    return "$operator$currency$amountString"
}