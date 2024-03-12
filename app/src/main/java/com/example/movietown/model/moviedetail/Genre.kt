package com.example.movietown.model.moviedetail

data class Genre(
    val id: Int?,
    val name: String,
    var isSelected: Boolean = false
)