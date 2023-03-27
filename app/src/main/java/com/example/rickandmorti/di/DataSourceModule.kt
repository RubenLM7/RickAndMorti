package com.example.rickandmorti.di

import android.content.SharedPreferences
import com.example.data.apis.CharactersApi
import com.example.data.datasource.LocalCharactersDataSource
import com.example.data.datasource.LocalCharactersDataSourceImp
import com.example.data.datasource.NetworkCharactersDataSource
import com.example.data.datasource.NetworkCharactersDataSourceImp
import org.koin.dsl.module

val dataSourceModule = module {

    factory { provideLocalCharactersDataSource(get()) }
    factory { provideNetworkCharactersDataSource(get()) }
}

private fun provideLocalCharactersDataSource(sharedPreferences: SharedPreferences): LocalCharactersDataSource {
    return LocalCharactersDataSourceImp(sharedPreferences)
}

private fun provideNetworkCharactersDataSource(charactersApi: CharactersApi): NetworkCharactersDataSource {
    return NetworkCharactersDataSourceImp(charactersApi)
}

