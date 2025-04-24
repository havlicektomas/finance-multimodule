package dev.havlicektomas.finance.presentation.transaction_overview

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.havlicektomas.core.domain.finance.FinanceTransaction
import dev.havlicektomas.core.domain.util.Result
import dev.havlicektomas.finance.domain.TransactionRepository
import dev.havlicektomas.finance.presentation.mapper.financeTransactionsToTransactionsPerDay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TransactionOverviewViewModel(
    private val repository: TransactionRepository
): ViewModel() {

    val state: StateFlow<TransactionOverviewState> = repository.transactionsFlow()
        .map { transactions ->
            TransactionOverviewState(
                latestTransactions = financeTransactionsToTransactionsPerDay(transactions)
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = TransactionOverviewState()
        )

    fun onAction(action: TransactionOverviewAction) {
        //
    }
}