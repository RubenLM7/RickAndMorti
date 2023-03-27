package com.example.data.apis.model

data class InfoDTO (
    var count: Int,
    var pages: Int,
    var next: String,
    var prev: Nothing? = null,
)