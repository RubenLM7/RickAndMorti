package com.example.domain

import com.example.domain.model.*
import com.example.domain.repositories.CharactersRepository
import com.example.domain.usecases.GetCharactersListUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetCharactersListUseCaseUnitTest {

    private lateinit var useCase: GetCharactersListUseCase
    private lateinit var result: GetCharactersListUseCase.Output
    private val charactersRepository = mockk<CharactersRepository>()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
    }

    @Test
    fun `given a object , when I call the characterRepository, then the useCase returns a getCharacters()`() {

        val listCharactersWrapper = ListCharactersWrapper(Info(20,2,"https://rickandmortyapi.com/api/character/?page=2", null),
        listOf(Characters(1,"Ricky", "Alive", "Human", "", "Male", Origin("Earth", "http:..."),
            Location("Earth", "http..."),"https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            listOf("http1", "http2"), "htttp...", "")))

        val resultSuccessExpected = GetCharactersListUseCase.Output.Success(listCharactersWrapper)

        every {
            runBlocking {
                charactersRepository.getCharacters()
            }
        } answers { Result.success(listCharactersWrapper) }


        useCase = GetCharactersListUseCase(charactersRepository)
        runBlocking {
            useCase.prepare(Unit).collect { result = it }
        }
        when (result) {
            is GetCharactersListUseCase.Output.Success -> {
                val result = (result as GetCharactersListUseCase.Output.Success)
                assertEquals(resultSuccessExpected, result)
                println("expected: $resultSuccessExpected, result: $result")
            }
            GetCharactersListUseCase.Output.UnknownError -> {
                Assert.fail()
            }
        }
    }

    @Test
    fun `given a failure unknownError, when I call the characterRepository, then the useCase returns a getCharacters()`() {

        val resultFailureExpected = GetCharactersListUseCase.Output.UnknownError

        every {
            runBlocking {
                charactersRepository.getCharacters()
            }
        } answers { Result.failure(Throwable()) }

        useCase = GetCharactersListUseCase(charactersRepository)
        runBlocking {
            useCase.prepare(Unit).collect { result = it }
        }

        when (result) {
            is GetCharactersListUseCase.Output.Success -> {
                Assert.fail()
            }
            GetCharactersListUseCase.Output.UnknownError -> {
                val result = (result as GetCharactersListUseCase.Output.UnknownError)
                assertEquals(resultFailureExpected, result)
                println("expected: $resultFailureExpected, result: $result")
            }
        }
    }

}