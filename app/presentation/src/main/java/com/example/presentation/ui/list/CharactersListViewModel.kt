package com.example.presentation.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetCharactersListUseCase
import com.example.presentation.R
import com.example.presentation.ui.common.CharactersUiState
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CharactersListViewModel(private val charactersListUseCase: GetCharactersListUseCase): ViewModel() {

    private val _charactersUiState = MutableStateFlow<CharactersUiState>(CharactersUiState.Idle)
    val charactersUiState: StateFlow<CharactersUiState> = _charactersUiState


    fun getCharactersList() {
        viewModelScope.launch {
            charactersListUseCase.prepare(Unit)
                .transform { output: GetCharactersListUseCase.Output ->
                    val state: CharactersUiState = when (output) {

                        is GetCharactersListUseCase.Output.Success -> {
                            CharactersUiState.OnSuccess(output.list)
                        }
                        GetCharactersListUseCase.Output.UnknownError -> {
                            CharactersUiState.OnError(R.string.error.toString())
                        }
                    }
                    emit(state)
                }.catch { exception ->
                    if(exception is TimeoutCancellationException){
                        emit(CharactersUiState.Timeout)
                    } else {
                        emit(CharactersUiState.OnError(exception.message ?: R.string.error.toString()))
                    }
                }.onStart {
                    emit(CharactersUiState.Initial)
                }.collect {
                    _charactersUiState.value = it
                }
        }
    }
}