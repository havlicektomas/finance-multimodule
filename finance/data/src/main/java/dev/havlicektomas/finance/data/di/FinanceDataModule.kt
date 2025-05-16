package dev.havlicektomas.finance.data.di

import dev.havlicektomas.core.domain.finance.RemoteTransactionDataSource
import dev.havlicektomas.core.domain.finance.TransactionRepository
import dev.havlicektomas.finance.data.TransactionRepositoryImpl
import dev.havlicektomas.finance.data.networking.KtorRemoteTransactionDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val financeDataModule = module {
    singleOf(::KtorRemoteTransactionDataSource).bind<RemoteTransactionDataSource>()
    singleOf(::TransactionRepositoryImpl).bind<TransactionRepository>()
}