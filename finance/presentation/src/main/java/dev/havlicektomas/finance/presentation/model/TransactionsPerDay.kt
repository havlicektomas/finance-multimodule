package dev.havlicektomas.finance.presentation.model

import dev.havlicektomas.core.presentation.ui.UiText

data class TransactionsPerDay(
    val date: UiText,
    val transactions: List<UITransaction>
)
