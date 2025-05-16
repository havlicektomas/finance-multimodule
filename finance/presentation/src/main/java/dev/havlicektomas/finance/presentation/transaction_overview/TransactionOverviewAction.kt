package dev.havlicektomas.finance.presentation.transaction_overview

sealed interface TransactionOverviewAction {
    data object OnAddTransactionClick: TransactionOverviewAction
    data object OnShowAllTransactionsClick: TransactionOverviewAction
    data object OnSettingsClick: TransactionOverviewAction
}