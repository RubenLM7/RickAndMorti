package com.example.presentation.ui.common

import com.example.domain.model.ListCharactersWrapper


sealed class CharactersUiState(val showLoading: Boolean) {
    object Idle : CharactersUiState(false)
    object Initial : CharactersUiState(true)
    data class OnSuccess(val result: ListCharactersWrapper) : CharactersUiState(false)
    data class OnError(val error: String) : CharactersUiState(false)
    object Timeout : CharactersUiState(false)
}