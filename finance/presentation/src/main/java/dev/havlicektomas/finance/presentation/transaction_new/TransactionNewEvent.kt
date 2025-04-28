package dev.havlicektomas.finance.presentation.transaction_new

import dev.havlicektomas.core.presentation.ui.UiText

sealed interface TransactionNewEvent {
    data class Error(val error: UiText): TransactionNewEvent
    data object TransactionSaved: TransactionNewEvent
}