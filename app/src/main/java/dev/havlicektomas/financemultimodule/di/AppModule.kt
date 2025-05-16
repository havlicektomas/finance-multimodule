package dev.havlicektomas.financemultimodule.di

import dev.havlicektomas.financemultimodule.FinanceApp
import dev.havlicektomas.financemultimodule.MainViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<CoroutineScope> {
        (androidApplication() as FinanceApp).applicationScope
    }
    viewModelOf(::MainViewModel)
}