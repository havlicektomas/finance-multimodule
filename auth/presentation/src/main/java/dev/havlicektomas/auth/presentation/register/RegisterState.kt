package dev.havlicektomas.auth.presentation.register

import androidx.compose.foundation.text.input.TextFieldState
import dev.havlicektomas.auth.domain.PasswordValidationState

data class RegisterState(
    val email: String = "",
    val isEmailValid: Boolean = false,
    val password: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val passwordValidationState: PasswordValidationState = PasswordValidationState(),
    val isRegistering: Boolean = false,
    val canRegister: Boolean = passwordValidationState.isValidPassword && !isRegistering
)