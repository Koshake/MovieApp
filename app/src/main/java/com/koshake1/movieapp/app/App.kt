package com.koshake1.movieapp.app

import android.app.Application
import com.koshake1.movieapp.di.modules.dataSourceModule
import com.koshake1.movieapp.di.modules.repositoryModule
import com.koshake1.movieapp.di.modules.retrofitModule
import com.koshake1.movieapp.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            loadKoinModules(
                listOf(
                    retrofitModule,
                    dataSourceModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}