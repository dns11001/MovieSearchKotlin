package ru.gb.moviesearchkotlin.model.movie

data class Result(
    val description: String,
    val id: String,
    val image: String,
    val resultType: String,
    val title: String
)