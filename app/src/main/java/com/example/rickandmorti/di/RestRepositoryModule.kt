package com.example.rickandmorti.di

import com.example.data.CharactersRepositoryImp
import com.example.data.datasource.LocalCharactersDataSource
import com.example.data.datasource.NetworkCharactersDataSource
import com.example.domain.repositories.CharactersRepository
import org.koin.dsl.module

val restRepositoryModule = module {

    factory { provideCharactersRepository(get(), get()) }
}

private fun provideCharactersRepository(localCharactersDataSource: LocalCharactersDataSource, networkCharactersDataSource: NetworkCharactersDataSource): CharactersRepository {
    return CharactersRepositoryImp(localCharactersDataSource, networkCharactersDataSource)
}
