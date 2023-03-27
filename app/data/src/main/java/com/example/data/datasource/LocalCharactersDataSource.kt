package com.example.data.datasource

import com.example.data.apis.model.LocalListCharactersWrapperDTO


interface LocalCharactersDataSource {
    fun getCharacters(): LocalListCharactersWrapperDTO?
    fun setCharacters(local:LocalListCharactersWrapperDTO?)
}