package com.example.domain.model

data class Info (
    var count: Int,
    var pages: Int,
    var next: String,
    var prev: Nothing? = null,
)