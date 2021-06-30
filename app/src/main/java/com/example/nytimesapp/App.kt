package com.example.nytimesapp

import android.app.Application
import com.example.nytimesapp.di.apiModule
import com.example.nytimesapp.di.networkModule
import com.example.nytimesapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                apiModule,
                viewModelModule,
                networkModule
            )
        }
    }
}