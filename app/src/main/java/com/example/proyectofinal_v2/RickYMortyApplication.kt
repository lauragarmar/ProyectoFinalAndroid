package com.example.proyectofinal_v2

import android.app.Application
import com.example.proyectofinal_v2.di.dataModule
import com.example.proyectofinal_v2.di.domainModule
import com.example.proyectofinal_v2.di.presentationModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class RickYMortyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(
                if (BuildConfig.DEBUG) {
                    Level.INFO
                } else {
                    Level.NONE
                }
            )

            androidContext(this@RickYMortyApplication)
            modules(
                presentationModule,
                domainModule,
                dataModule
            )
        }
    }
}