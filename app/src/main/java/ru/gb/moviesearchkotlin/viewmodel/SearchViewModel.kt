package ru.gb.moviesearchkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep
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