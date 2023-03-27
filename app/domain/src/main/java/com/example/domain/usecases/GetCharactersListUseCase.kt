package com.example.domain.usecases

import com.example.domain.core.FlowUseCase
import com.example.domain.EWP_TIMEOUT
import com.example.domain.model.ListCharactersWrapper
import com.example.domain.repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout

class GetCharactersListUseCase(private val repository: CharactersRepository) : FlowUseCase<Unit, GetCharactersListUseCase.Output>() {
    sealed class Output {
        data class Success(val list: ListCharactersWrapper) : Output()
        object UnknownError : Output()
    }

    override fun executeIOFlow(input: Unit): Flow<Output> = flow {
        withTimeout(EWP_TIMEOUT) {
            repository.getCharacters()
                .onSuccess { result ->
                    emit(Output.Success(result))
                }.onFailure {
                    emit(Output.UnknownError)
                }
        }
    }
}
