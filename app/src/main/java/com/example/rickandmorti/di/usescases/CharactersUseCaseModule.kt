package com.example.rickandmorti.di.usescases

import com.example.domain.repositories.CharactersRepository
import com.example.domain.usecases.GetCharactersListUseCase
import org.koin.dsl.module

val charatersUseCaseModule = module {
    factory { provideGetCharactersListUseCase(get()) }

}

private fun provideGetCharactersListUseCase(repository: CharactersRepository): GetCharactersListUseCase {
    return GetCharactersListUseCase(repository)
}


