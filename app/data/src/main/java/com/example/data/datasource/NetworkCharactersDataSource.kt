package com.example.data.datasource

import com.example.data.apis.model.ListCharactersWrapperDTO
import retrofit2.Response

interface NetworkCharactersDataSource {
    fun getCharacters(): Response<ListCharactersWrapperDTO>
}