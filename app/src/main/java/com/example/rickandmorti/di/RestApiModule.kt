package com.example.rickandmorti.di

import com.example.data.apis.CharactersApi
import org.koin.dsl.module
import retrofit2.Retrofit

val restApiModule = module {
    factory { provideCharactersApi(get()) }
}

private fun provideCharactersApi(retrofit: Retrofit): CharactersApi = retrofit.create(CharactersApi::class.java)
