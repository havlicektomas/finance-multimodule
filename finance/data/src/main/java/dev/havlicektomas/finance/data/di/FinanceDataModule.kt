package dev.havlicektomas.finance.data.di

import dev.havlicektomas.finance.data.TransactionRepositoryImpl
import dev.havlicektomas.finance.domain.TransactionRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val financeDataModule = module {
    singleOf(::TransactionRepositoryImpl).bind<TransactionRepository>()
}