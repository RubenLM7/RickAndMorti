package com.example.data.datasource

import com.example.data.apis.CharactersApi
import com.example.data.apis.model.ListCharactersWrapperDTO
import retrofit2.Response

class NetworkCharactersDataSourceImp(private val charactersApi: CharactersApi) : NetworkCharactersDataSource {
    override fun getCharacters(): Response<ListCharactersWrapperDTO> {
        return charactersApi.getCharacters().execute()
    }
}
