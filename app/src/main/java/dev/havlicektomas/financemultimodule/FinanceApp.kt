package dev.havlicektomas.financemultimodule

import android.app.Application
import dev.havlicektomas.auth.data.di.authDataModule
import dev.havlicektomas.auth.presentation.di.authViewModelModule
import dev.havlicektomas.core.data.di.coreDataModule
import dev.havlicektomas.financemultimodule.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class FinanceApp: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@FinanceApp)
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule
            )
        }
    }
}