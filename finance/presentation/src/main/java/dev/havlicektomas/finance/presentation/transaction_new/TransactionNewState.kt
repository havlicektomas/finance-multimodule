package dev.havlicektomas.finance.presentation.transaction_new

import dev.havlicektomas.finance.presentation.model.UITransaction

data class TransactionNewState(
    val transaction: UITransaction = UITransaction(),
    val isSavingTransaction: Boolean = false,
    val canCreateTransaction: Boolean = false
)
