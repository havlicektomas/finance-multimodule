package dev.havlicektomas.auth.presentation.di

import dev.havlicektomas.auth.presentation.login.LoginViewModel
import dev.havlicektomas.auth.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}