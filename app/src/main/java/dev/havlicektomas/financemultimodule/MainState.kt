package dev.havlicektomas.financemultimodule

data class MainState(
    val isLoggedIn: Boolean = false,
    val isCheckingAuth: Boolean = false
)