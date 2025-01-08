package dev.havlicektomas.auth.presentation.register

sealed interface RegisterAction {
    data class OnEmailChange(val email: String): RegisterAction
    data object OnTogglePasswordVisibilityClick: RegisterAction
    data object OnLoginClick: RegisterAction
    data object OnRegisterClick: RegisterAction
}