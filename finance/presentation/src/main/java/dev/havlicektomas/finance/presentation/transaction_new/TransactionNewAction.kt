package dev.havlicektomas.finance.presentation.transaction_new

sealed interface TransactionNewAction {
    data class OnTransactionTypeSelected(val type: String): TransactionNewAction
    data class OnTransactionTitleChanged(val title: String): TransactionNewAction
    data class OnTransactionAmountChanged(val amount: Double): TransactionNewAction
    data class OnTransactionNoteChanged(val note: String): TransactionNewAction
    data class OnTransactionCategorySelected(val category: String): TransactionNewAction
    data object OnCreateTransactionClick: TransactionNewAction
    data object OnBackClick: TransactionNewAction
}