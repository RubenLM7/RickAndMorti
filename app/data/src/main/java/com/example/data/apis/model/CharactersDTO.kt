package com.example.data.apis.model

data class CharactersDTO (
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: Origin,
    var location: Location,
    var image: String,
    var episode: List<String>,
    var url: String,
    var created: String
)


data class Origin(
    var name:String,
    var url: String
)

data class Location(
    var name: String,
    var url: String
)



