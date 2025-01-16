package dev.havlicektomas.auth.data.di

import dev.havlicektomas.auth.data.EmailPatternValidator
import dev.havlicektomas.auth.data.FakeAuthRepositoryImpl
import dev.havlicektomas.auth.domain.AuthRepository
import dev.havlicektomas.auth.domain.PatternValidator
import dev.havlicektomas.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
    singleOf(::FakeAuthRepositoryImpl).bind<AuthRepository>()
}