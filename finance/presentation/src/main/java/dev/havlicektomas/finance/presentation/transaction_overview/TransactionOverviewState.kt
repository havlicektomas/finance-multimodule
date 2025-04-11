package dev.havlicektomas.finance.presentation.transaction_overview

import dev.havlicektomas.core.presentation.designsystem.components.TransactionsPerDay

data class TransactionOverviewState(
    val username: String = "",
    val accountBalance: Double = 0.0,
    val largestTransactionAmount: Double = 0.0,
    val largestTransactionDate: String = "",
    val largestTransactionTitle: String = "",
    val thisWeekSum: Double = 0.0,
    val latestTransactions: List<TransactionsPerDay> = emptyList()
)
