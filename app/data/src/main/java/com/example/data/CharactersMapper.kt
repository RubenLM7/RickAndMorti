package com.example.data

import com.example.data.apis.model.CharactersDTO
import com.example.data.apis.model.InfoDTO
import com.example.data.apis.model.ListCharactersWrapperDTO
import com.example.domain.model.*

fun InfoDTO.mapToDomain() = Info(count, pages, next, prev)

fun CharactersDTO.mapToDomain() = Characters(id,name, status, species, type, gender, Origin(name, url), Location(name, url), image, episode, url, created)

fun ListCharactersWrapperDTO.mapToDomain() = ListCharactersWrapper(
    info = info.mapToDomain(),
    listCharacters = listCharacters.map { it.mapToDomain()}
)