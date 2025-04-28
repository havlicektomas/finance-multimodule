package dev.havlicektomas.finance.presentation.transaction_new

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.havlicektomas.core.domain.util.Result
import dev.havlicektomas.core.presentation.ui.asUiText
import dev.havlicektomas.finance.domain.TransactionRepository
import dev.havlicektomas.finance.presentation.mapper.toDomainTransaction
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TransactionNewViewModel(
    private val repository: TransactionRepository
): ViewModel() {

    var state by mutableStateOf(TransactionNewState())
        private set

    private val eventChannel = Channel<TransactionNewEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: TransactionNewAction) {
        when (action) {
            TransactionNewAction.OnBackClick -> {
                // handled by override in TransactionNewScreenRoot
            }
            TransactionNewAction.OnCreateTransactionClick -> {
                createTransaction()
            }
            is TransactionNewAction.OnTransactionAmountChanged -> {
                val newTransaction = state.transaction.copy(
                    amount = action.amount
                )
                state = state.copy(
                    transaction = newTransaction,
                    canCreateTransaction = isValidTransaction(action.amount)
                )
            }
            is TransactionNewAction.OnTransactionCategorySelected -> {
                state = state.copy(
                    transaction = state.transaction.copy(
                        category = action.category
                    )
                )
            }
            is TransactionNewAction.OnTransactionNoteChanged -> {
                state = state.copy(
                    transaction = state.transaction.copy(
                        note = action.note
                    )
                )
            }
            is TransactionNewAction.OnTransactionTitleChanged -> {
                state = state.copy(
                    transaction = state.transaction.copy(
                        title = action.title
                    )
                )
            }
            is TransactionNewAction.OnTransactionTypeSelected -> {
                state = state.copy(
                    transaction = state.transaction.copy(
                        type = action.type
                    )
                )
            }
        }
    }

    private fun isValidTransaction(amount: String): Boolean {
        return amount.isDigitsOnly() && amount.toDouble() > 0.0
    }

    private fun createTransaction() {
        val transaction = state.transaction.toDomainTransaction()

        viewModelScope.launch {
            state = state.copy(isSavingTransaction = true)
            val result = repository.createTransaction(transaction)
            state = state.copy(isSavingTransaction = false)

            when(result) {
                is Result.Error -> {
                    eventChannel.send(TransactionNewEvent.Error(result.error.asUiText()))
                }
                is Result.Success -> {
                    eventChannel.send(TransactionNewEvent.TransactionSaved)
                }
            }
        }
    }
}