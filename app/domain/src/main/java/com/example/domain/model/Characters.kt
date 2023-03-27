package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Characters(
    var id: Int,
    var name: String?,
    var status: String?,
    var species: String?,
    var type: String?,
    var gender: String?,
    var origin: Origin?,
    var location: Location?,
    var image: String?,
    var episode: List<String>?,
    var url: String?,
    var created: String?
): Parcelable {
    constructor() : this(
        -1, null, null, null, null, null, null, null, null, null, null, null
    )
}

@Parcelize
data class Origin(
    var name:String,
    var url: String
): Parcelable

@Parcelize
data class Location(
    var name: String,
    var url: String
): Parcelable



