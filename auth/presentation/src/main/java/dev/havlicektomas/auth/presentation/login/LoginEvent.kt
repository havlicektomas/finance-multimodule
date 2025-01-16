package dev.havlicektomas.auth.presentation.login

import dev.havlicektomas.core.presentation.ui.UiText

sealed interface LoginEvent {
    data class Error(val error: UiText): LoginEvent
    data object LoginSuccess: LoginEvent
}