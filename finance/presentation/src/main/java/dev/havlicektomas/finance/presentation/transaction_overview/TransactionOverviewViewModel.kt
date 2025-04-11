package dev.havlicektomas.finance.presentation.transaction_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.havlicektomas.finance.domain.TransactionRepository

class TransactionOverviewViewModel(
    private val repository: TransactionRepository
): ViewModel() {

    var state by mutableStateOf(TransactionOverviewState())
        private set

    fun onAction(action: TransactionOverviewAction) {
        //
    }
}