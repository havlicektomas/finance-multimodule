package dev.havlicektomas.core.data.di

import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dev.havlicektomas.core.data.auth.EncryptedSessionStorage
import dev.havlicektomas.core.data.database.FinanceDatabase
import dev.havlicektomas.core.data.database.RoomLocalTransactionDataSource
import dev.havlicektomas.core.data.networking.HttpClientFactory
import dev.havlicektomas.core.domain.auth.SessionStorage
import dev.havlicektomas.core.domain.finance.LocalTransactionDataSource
import io.ktor.client.engine.cio.CIO
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build(CIO.create())
    }
    single<SharedPreferences> {
        EncryptedSharedPreferences(
            androidApplication(),
            "auth_pref",
            MasterKey(androidApplication()),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
    single {
        Room.databaseBuilder(
            androidApplication(),
            FinanceDatabase::class.java,
            "finance.db"
        ).build()
    }
    single { 
        get<FinanceDatabase>().transactionDao
    }
    singleOf(::RoomLocalTransactionDataSource).bind<LocalTransactionDataSource>()
}