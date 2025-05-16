package dev.havlicektomas.finance.presentation.transaction_overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.havlicektomas.core.domain.finance.TransactionRepository
import dev.havlicektomas.finance.presentation.mapper.financeTransactionsToTransactionsPerDay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TransactionOverviewViewModel(
    private val repository: TransactionRepository
): ViewModel() {

    val state: StateFlow<TransactionOverviewState> = repository.transactionsFlow()
        .map { transactions ->
            TransactionOverviewState(
                username = "test@test.com",
                accountBalance = "$10.000.00",
                largestTransactionDate = "Jan 07, 2025",
                largestTransactionAmount = "-$600.00",
                largestTransactionTitle = "Purchase",
                thisWeekSum = "$200.00",
                latestTransactions = financeTransactionsToTransactionsPerDay(transactions)
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = TransactionOverviewState()
        )

    fun onAction(action: TransactionOverviewAction) {
        when (action) {
            TransactionOverviewAction.OnAddTransactionClick -> {
                // handled by override in TransactionOverviewScreenRoot
            }
            TransactionOverviewAction.OnSettingsClick -> TODO()
            TransactionOverviewAction.OnShowAllTransactionsClick -> TODO()
        }
    }
}