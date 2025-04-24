package dev.havlicektomas.finance.presentation.transaction_new

import dev.havlicektomas.core.domain.finance.FinanceTransactionCategory
import dev.havlicektomas.core.domain.finance.FinanceTransactionType

sealed interface TransactionNewAction {
    data class OnTransactionTypeSelected(val type: FinanceTransactionType): TransactionNewAction
    data class OnTransactionTitleChanged(val title: String): TransactionNewAction
    data class OnTransactionAmountChanged(val amount: String): TransactionNewAction
    data class OnTransactionNoteChanged(val note: String): TransactionNewAction
    data class OnTransactionCategorySelected(val category: FinanceTransactionCategory): TransactionNewAction
    data object OnCreateTransactionClick: TransactionNewAction
    data object OnBackClick: TransactionNewAction
}