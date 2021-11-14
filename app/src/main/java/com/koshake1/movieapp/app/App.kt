package com.koshake1.movieapp.app

import android.app.Application
import com.koshake1.movieapp.di.modules.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                retrofitModule,
            )
        }
    }
}