package dev.havlicektomas.financemultimodule

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Auth: Route
}

sealed interface AuthRoute {
    @Serializable
    data object Intro: AuthRoute

    @Serializable
    data object Login: AuthRoute

    @Serializable
    data object Register: AuthRoute
}