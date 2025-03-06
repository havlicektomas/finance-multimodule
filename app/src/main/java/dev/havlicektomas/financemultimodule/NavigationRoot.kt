package dev.havlicektomas.financemultimodule

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.havlicektomas.auth.presentation.intro.IntroScreenRoot
import dev.havlicektomas.auth.presentation.login.LoginScreenRoot
import dev.havlicektomas.auth.presentation.register.RegisterScreenRoot
import dev.havlicektomas.finance.presentation.transaction_overview.TransactionOverviewScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController,
    isLoggedIn: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Route.Finance else Route.Auth
    ) {
        authGraph(navController)
        financeGraph(navController)
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<Route.Auth>(
        startDestination = AuthRoute.Intro
    ) {
        composable<AuthRoute.Intro> {
            IntroScreenRoot(
                onSignUpClick = {
                    navController.navigate(AuthRoute.Register)
                },
                onSignInClick = {
                    navController.navigate(AuthRoute.Login)
                }
            )
        }
        composable<AuthRoute.Register> {
            RegisterScreenRoot(
                onSignInClick = {
                    navController.navigate(AuthRoute.Login) {
                        popUpTo(AuthRoute.Register) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSuccessfulRegistration = {
                    navController.navigate(AuthRoute.Login)
                }
            )
        }
        composable<AuthRoute.Login> {
            LoginScreenRoot(
                onSignUpClick = {
                    navController.navigate(AuthRoute.Register) {
                        popUpTo(AuthRoute.Login) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onLoginSuccess = {
                    navController.navigate(Route.Finance) {
                        popUpTo(Route.Auth) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

private fun NavGraphBuilder.financeGraph(navController: NavHostController) {
    navigation<Route.Finance>(
        startDestination = FinanceRoute.Overview
    ) {
        composable<FinanceRoute.Overview> {
            TransactionOverviewScreenRoot()
        }
    }
}