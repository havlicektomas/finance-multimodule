package dev.havlicektomas.financemultimodule.di

import dev.havlicektomas.financemultimodule.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainViewModel)
}