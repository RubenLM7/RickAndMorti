package com.example.data

import com.example.data.apis.model.ListCharactersWrapperDTO
import com.example.data.apis.model.LocalListCharactersWrapperDTO
import com.example.data.datasource.LocalCharactersDataSource
import com.example.data.datasource.NetworkCharactersDataSource
import com.example.domain.model.ListCharactersWrapper
import com.example.domain.repositories.CharactersRepository
import java.util.*

class CharactersRepositoryImp(private val localCharactersDataSource: LocalCharactersDataSource,
                              private val characterDataSource: NetworkCharactersDataSource) : CharactersRepository {

    override fun getCharacters(): Result<ListCharactersWrapper> {
        val local= getCharacterLocal()
        local?.let {
            return Result.success(it.mapToDomain())
        } ?: return getCharactersNetwork()
    }

    private fun getCharacterLocal(): ListCharactersWrapperDTO? {
        val result = localCharactersDataSource.getCharacters()
        result?.let {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.HOUR_OF_DAY, -1)
            if (it.date.after(calendar)){
                return it.dto
            }else{
                return null
            }
        } ?: return null
    }

    private fun getCharactersNetwork(): Result<ListCharactersWrapper> {
        val result = characterDataSource.getCharacters()
        val data = result.body()
        return if (result.isSuccessful && data != null) {
            localCharactersDataSource.setCharacters(LocalListCharactersWrapperDTO(data, Calendar.getInstance()))
            Result.success(data.mapToDomain())
        } else {
            Result.failure(
                Throwable(
                    result.code().toString()
                )
            )
        }
    }
}
