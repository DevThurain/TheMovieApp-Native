package com.thurainx.themovieapplication.mvi.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thurainx.themovieapplication.mvi.intent.MainIntent
import com.thurainx.themovieapplication.mvi.mviBased.MVIViewModel
import com.thurainx.themovieapplication.mvi.processor.MainProcessor
import com.thurainx.themovieapplication.mvi.state.MainState

class MainViewModel(override val state: MutableLiveData<MainState> = MutableLiveData(MainState.idle())) :
    MVIViewModel<MainState, MainIntent>, ViewModel() {

    private val mainProcessor = MainProcessor

    override fun processIntent(intent: MainIntent, owner: LifecycleOwner) {
        when (intent) {
            is MainIntent.getHomePageData -> {
                state.value?.let {

                    mainProcessor.getHomePageData(previousState = it).observe(owner) { newState ->
                        if (newState.genreList.isNotEmpty()) {
                            processIntent(intent = MainIntent.getMovieListByGenre(0), owner)
                        }
                        state.postValue(newState)
                    }
                }
            }

            is MainIntent.getMovieListByGenre -> {
                state.value?.let {
                    val genreId = it.genreList.getOrNull(intent.position)?.id ?: 0
                    mainProcessor.getMovieListByGenre(genderId = genreId, previousState = it)
                        .observe(owner) { newState ->
                            state.postValue(newState)
                        }
                }
            }
        }
    }
}