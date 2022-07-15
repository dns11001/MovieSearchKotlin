package ru.gb.moviesearchkotlin.viewmodel

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import ru.gb.moviesearchkotlin.R
import ru.gb.moviesearchkotlin.model.movie.MovieDTO
import ru.gb.moviesearchkotlin.model.movie.getLines
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Thread.sleep
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.random.Random

class SearchViewModel(val liveData:MutableLiveData<AppState> = MutableLiveData<AppState>()):ViewModel() {

    fun sentRequest() {
        val random = Random.nextBoolean()
        liveData.value = AppState.Loading
        Thread{
            sleep(2000L)
            when (random) {
                (true) -> liveData.postValue(AppState.Success(Any()))
                (false) -> liveData.postValue(AppState.Error(Throwable()))
            }
        }.start()
    }

}