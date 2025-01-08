package dev.havlicektomas.financemultimodule

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.havlicektomas.auth.presentation.intro.IntroScreenRoot
import dev.havlicektomas.auth.presentation.register.RegisterScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Route.Auth
    ) {
        authGraph(navController)
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
            Text(text = "Login")
        }
    }
}