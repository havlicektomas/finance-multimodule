package dev.havlicektomas.finance.presentation.transaction_new.di

import dev.havlicektomas.finance.presentation.transaction_new.TransactionNewViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val transactionNewViewModelModule = module {
    viewModelOf(::TransactionNewViewModel)
}