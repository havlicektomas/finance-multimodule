package dev.havlicektomas.finance.presentation.transaction_overview

import dev.havlicektomas.finance.presentation.model.TransactionsPerDay

data class TransactionOverviewState(
    val username: String = "",
    val accountBalance: String = "",
    val largestTransactionAmount: String = "",
    val largestTransactionDate: String = "",
    val largestTransactionTitle: String = "",
    val thisWeekSum: String = "",
    val latestTransactions: List<TransactionsPerDay> = emptyList()
)
