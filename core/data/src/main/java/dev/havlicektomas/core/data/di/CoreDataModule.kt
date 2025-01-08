package dev.havlicektomas.core.data.di

import dev.havlicektomas.core.data.networking.HttpClientFactory
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory().build()
    }
}