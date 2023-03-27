package com.example.rickandmorti.di

import com.example.presentation.ui.detail.DetailCharacterViewModel
import com.example.presentation.ui.list.CharactersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val scopesModule = module {
    viewModel { CharactersListViewModel(get()) }
    viewModel { DetailCharacterViewModel() }
}