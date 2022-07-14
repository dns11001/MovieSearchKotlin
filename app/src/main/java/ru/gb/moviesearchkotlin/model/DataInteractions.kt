package ru.gb.moviesearchkotlin.model

interface DataInteractions {
    fun request()
    fun initPopularMovieList()
    fun requestUpcomingMovie()
}