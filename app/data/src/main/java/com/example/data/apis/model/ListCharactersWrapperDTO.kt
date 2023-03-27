package com.example.data.apis.model

import com.google.gson.annotations.SerializedName
import java.util.Date


data class ListCharactersWrapperDTO (
    @SerializedName("info")
    var info: InfoDTO,
    @SerializedName("results")
    var listCharacters: List<CharactersDTO>
)