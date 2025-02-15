package dev.anapsil.chucknorris

import android.app.Application
import dev.anapsil.chucknorris.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@Application)
            modules(appModule)
        }
    }
}