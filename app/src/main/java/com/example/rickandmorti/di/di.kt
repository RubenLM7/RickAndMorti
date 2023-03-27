package com.example.rickandmorti.di

import android.app.Application
import com.example.rickandmorti.di.usescases.charatersUseCaseModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        koin.loadModules(
            listOf(
                appModule,
                scopesModule,
                networkModule,
                dataSourceModule,
                restApiModule,
                localSharedPreferencesModule,
                restRepositoryModule,
                charatersUseCaseModule,
            )
        )
    }
}

private val appModule = module {
    single<CoroutineDispatcher> { Dispatchers.Main }
}