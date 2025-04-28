package dev.havlicektomas.finance.presentation.di

import dev.havlicektomas.finance.presentation.transaction_new.TransactionNewViewModel
import dev.havlicektomas.finance.presentation.transaction_overview.TransactionOverviewViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val financePresentationModule = module {
    viewModelOf(::TransactionNewViewModel)
    viewModelOf(::TransactionOverviewViewModel)
}