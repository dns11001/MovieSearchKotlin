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
            if (!random) liveData.postValue(AppState.Success(Any()))
            else liveData.postValue(AppState.Error(Throwable()))
        }.start()
    }

}