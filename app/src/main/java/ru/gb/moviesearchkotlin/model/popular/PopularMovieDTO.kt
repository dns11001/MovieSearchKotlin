package ru.gb.moviesearchkotlin.model.popular

data class PopularMovieDTO(
    val errorMessage: String,
    val items: List<PopularMovie>
)