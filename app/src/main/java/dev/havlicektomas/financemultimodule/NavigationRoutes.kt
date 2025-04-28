package dev.havlicektomas.financemultimodule

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Auth: Route

    @Serializable
    data object Finance: Route
}

sealed interface AuthRoute {
    @Serializable
    data object Intro: AuthRoute

    @Serializable
    data object Login: AuthRoute

    @Serializable
    data object Register: AuthRoute
}

sealed interface FinanceRoute {
    @Serializable
    data object Overview: FinanceRoute

    @Serializable
    data object TransactionNew: FinanceRoute

    @Serializable
    data object Transactions: FinanceRoute
}