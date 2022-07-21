package ru.gb.moviesearchkotlin.model

import okhttp3.Callback

interface MovieRepository {
    fun getMovieDTOFromServer(requestLink: String, callback: Callback)
    fun getPopularMovieFromServer(requestLink: String, callback: Callback)
    fun getUpcomingMovieFromServer(requestLink: String, callback: Callback)
    fun initPopularMovieList()
    fun initUpcomingMovieList()
}