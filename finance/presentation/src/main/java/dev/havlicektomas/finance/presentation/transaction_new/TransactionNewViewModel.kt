package dev.havlicektomas.finance.presentation.transaction_new

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.havlicektomas.core.domain.finance.FinanceTransaction
import dev.havlicektomas.core.domain.util.DataError
import dev.havlicektomas.core.domain.util.Result
import dev.havlicektomas.core.presentation.ui.UiText
import dev.havlicektomas.core.presentation.ui.asUiText
import dev.havlicektomas.finance.domain.TransactionRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi

class TransactionNewViewModel(
    private val repository: TransactionRepository
): ViewModel() {

    var state by mutableStateOf(TransactionNewState())
        private set

    private val eventChannel = Channel<TransactionNewEvent>()
    val events = eventChannel.receiveAsFlow()

    @OptIn(ExperimentalUuidApi::class)
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
                    canCreateTransaction = isValidTransaction(newTransaction)
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

    private fun isValidTransaction(transaction: FinanceTransaction): Boolean {
        return transaction.amount > 0.0
    }

    private fun createTransaction() {
        viewModelScope.launch {
            state = state.copy(isSavingTransaction = true)
            val result = repository.createTransaction(state.transaction)
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