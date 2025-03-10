package dev.havlicektomas.finance.presentation.transaction_new

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class TransactionNewViewModel: ViewModel() {

    var state by mutableStateOf(TransactionNewState())
        private set

    private val eventChannel = Channel<TransactionNewEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: TransactionNewAction) {
        //
    }
}