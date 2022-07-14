package ru.gb.moviesearchkotlin.model

import android.content.res.Resources
import ru.gb.moviesearchkotlin.R
import java.net.URL
import javax.net.ssl.HttpsURLConnection

data class MovieDTO (
    val errorMessage: String,
    val expression: String,
    val results: List<Result>,
    val searchType: String
)

private fun initMovieList() : ArrayList<MovieDTO> {
    val arrayMovie: ArrayList<MovieDTO> = arrayListOf<MovieDTO>()
    var uriTemplate = "https://imdb-api.com/en/API/Search/k_4i110l81/"
    for (item in 0..5) {

        var internetConnection : HttpsURLConnection? = null
        arrayMovie.add(MovieDTO())
    }

    return arrayMovie
}