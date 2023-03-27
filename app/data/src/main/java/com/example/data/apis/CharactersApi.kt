package com.example.data.apis


import com.example.data.apis.model.ListCharactersWrapperDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CharactersApi {

    @Headers("Content-Type: application/json; charset=UTF-8")
    @GET("character")
    fun getCharacters(): Call<ListCharactersWrapperDTO>
}
