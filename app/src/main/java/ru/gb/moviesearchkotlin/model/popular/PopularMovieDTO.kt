package ru.gb.moviesearchkotlin.model.popular

data class PopularMovieDTO(
    val errorMessage: String,
    val items: List<PopularMovie>
)

data class PopularMovie(
    val crew: String,
    val fullTitle: String,
    val localizedTitle: String,
    val id: String,
    val imDbRating: String,
    val imDbRatingCount: String,
    val image: String,
    val rank: String,
    val rankUpDown: String,
    val title: String,
    val year: String
)