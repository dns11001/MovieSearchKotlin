package ru.gb.moviesearchkotlin.model.popular

data class Item(
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