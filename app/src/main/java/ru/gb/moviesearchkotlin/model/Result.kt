package ru.gb.moviesearchkotlin.model

data class Result(
    val description: String,
    val id: String,
    val image: String,
    val resultType: String,
    val title: String
)