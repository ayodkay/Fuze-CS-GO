package com.example.fuze

import android.app.Application
import com.example.fuze.di.PandaScoreServiceModule
import com.example.fuze.di.appModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FuzeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@FuzeApplication)
            // Load modules
            modules(appModule, PandaScoreServiceModule().pandaScoreModule())
        }
    }
}