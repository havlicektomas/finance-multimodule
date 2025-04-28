package dev.havlicektomas.finance.presentation.model

data class TransactionsPerDay(
    val date: String,
    val transactions: List<UITransaction>
)
