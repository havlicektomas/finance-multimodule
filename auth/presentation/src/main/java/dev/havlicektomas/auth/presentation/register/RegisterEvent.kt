package dev.havlicektomas.auth.presentation.register

import dev.havlicektomas.core.presentation.ui.UiText

sealed interface RegisterEvent {
    data object RegistrationSuccess: RegisterEvent
    data class Error(val error: UiText): RegisterEvent
}