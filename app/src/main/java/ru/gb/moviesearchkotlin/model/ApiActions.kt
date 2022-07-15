package ru.gb.moviesearchkotlin.model

import com.google.gson.Gson
import ru.gb.moviesearchkotlin.model.popular.PopularMovie
import ru.gb.moviesearchkotlin.model.popular.PopularMovieDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class ApiActions : DataInteractions {
    override fun request() {
    }

    public override fun initPopularMovieList() {
        val uri = URL("https://imdb-api.com/en/API/MostPopularTVs/k_4i110l81")
        var myConnection: HttpsURLConnection? = null

        myConnection = uri.openConnection() as HttpsURLConnection
        myConnection.readTimeout = 5000
        myConnection.addRequestProperty("k_4i110l81", "")
        Thread {
            val reader = BufferedReader(InputStreamReader(myConnection.inputStream))
            val gsonList = Gson().fromJson(reader, PopularMovieDTO::class.java).items

            for(item in 0..gsonList.size) {

            }

        }.start()
    }

    override fun requestUpcomingMovie() {
    }
}