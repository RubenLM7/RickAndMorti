package com.example.data.datasource

import android.content.SharedPreferences
import com.example.data.apis.model.LocalListCharactersWrapperDTO
import com.google.gson.Gson


class LocalCharactersDataSourceImp(private val sharedPreferences: SharedPreferences) : LocalCharactersDataSource {

    override fun getCharacters(): LocalListCharactersWrapperDTO? {
        val data = sharedPreferences.getString("characters", null) ?: return null
        return Gson().fromJson(data, LocalListCharactersWrapperDTO::class.java)
    }

    override fun setCharacters(local: LocalListCharactersWrapperDTO?) {
        sharedPreferences.edit().putString("characters", Gson().toJson(local)).apply()
    }
}