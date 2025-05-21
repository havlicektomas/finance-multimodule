package dev.havlicektomas.finance.data.di

import dev.havlicektomas.core.domain.finance.RemoteTransactionDataSource
import dev.havlicektomas.core.domain.finance.SyncTransactionScheduler
import dev.havlicektomas.core.domain.finance.TransactionRepository
import dev.havlicektomas.finance.data.TransactionRepositoryImpl
import dev.havlicektomas.finance.data.networking.KtorRemoteTransactionDataSource
import dev.havlicektomas.finance.data.worker.CreateTransactionWorker
import dev.havlicektomas.finance.data.worker.DeleteTransactionWorker
import dev.havlicektomas.finance.data.worker.FetchTransactionsWorker
import dev.havlicektomas.finance.data.worker.SyncTransactionWorkerScheduler
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val financeDataModule = module {
    singleOf(::KtorRemoteTransactionDataSource).bind<RemoteTransactionDataSource>()
    singleOf(::TransactionRepositoryImpl).bind<TransactionRepository>()

    workerOf(::CreateTransactionWorker)
    workerOf(::FetchTransactionsWorker)
    workerOf(::DeleteTransactionWorker)

    singleOf(::SyncTransactionWorkerScheduler).bind<SyncTransactionScheduler>()
}