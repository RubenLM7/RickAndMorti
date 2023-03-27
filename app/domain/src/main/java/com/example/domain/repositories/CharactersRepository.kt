package com.example.domain.repositories

import com.example.domain.model.ListCharactersWrapper

interface CharactersRepository {
    fun getCharacters(): Result<ListCharactersWrapper>
}