package ru.gb.moviesearchkotlin.model

import com.google.gson.Gson
import okhttp3.Callback
import ru.gb.moviesearchkotlin.model.popular.PopularMovie
import ru.gb.moviesearchkotlin.model.popular.PopularMovieDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class ApiActions : MovieRepository {
    override fun getMovieDTOFromServer(requestLink: String, callback: Callback) {
        TODO("Not yet implemented")
    }

    override fun getPopularMovieFromServer(requestLink: String, callback: Callback) {
        TODO("Not yet implemented")
    }

    override fun getUpcomingMovieFromServer(requestLink: String, callback: Callback) {
        TODO("Not yet implemented")
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

    override fun initUpcomingMovieList() {
        TODO("Not yet implemented")
    }

}