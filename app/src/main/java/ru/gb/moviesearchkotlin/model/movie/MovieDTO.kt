package ru.gb.moviesearchkotlin.model.movie

import android.content.res.Resources
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import ru.gb.moviesearchkotlin.R
import ru.gb.moviesearchkotlin.utils.Utils
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

data class MovieDTO(
    val errorMessage: String,
    val expression: String,
    val results: List<Result>,
    val searchType: String
)

@RequiresApi(Build.VERSION_CODES.N)
fun initMovieList(res: Resources): ArrayList<MovieDTO> {
    val arrayMovie: ArrayList<MovieDTO> = arrayListOf()

    for (i in 0..5) {

        val uri = URL(res.getStringArray(R.array.urls)[i])
        var internetConnection: HttpsURLConnection? = null
        var movie: MovieDTO

        internetConnection = uri.openConnection() as HttpsURLConnection
        internetConnection.readTimeout = 5000
        Thread {
            val reader = BufferedReader(InputStreamReader(internetConnection.inputStream))
            val result = getLines(reader)

            movie = Gson().fromJson(result, MovieDTO::class.java)
            arrayMovie.add(movie)

        }.start() // Как сделать, чтобы внутри Thread он раз за разом наполнял ArrayList и выдал несколько для RecyclerView?
    }

    return arrayMovie
}

@RequiresApi(Build.VERSION_CODES.N)
fun getLines(reader: BufferedReader): String {
    return reader.lines().collect(Collectors.joining("\n"))
}


