package dev.havlicektomas.finance.presentation.transaction_overview.di

import dev.havlicektomas.finance.presentation.transaction_overview.TransactionOverviewViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val transactionOverviewViewModelModule = module {
    viewModelOf(::TransactionOverviewViewModel)
}