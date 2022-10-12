package com.thurainx.themovieapplication.mvi.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thurainx.themovieapplication.mvi.intent.MovieDetailIntent
import com.thurainx.themovieapplication.mvi.mviBased.MVIViewModel
import com.thurainx.themovieapplication.mvi.processor.MovieDetailProcessor
import com.thurainx.themovieapplication.mvi.state.MovieDetailState

class MovieDetailViewModel(override val state : MutableLiveData<MovieDetailState> = MutableLiveData(MovieDetailState.idle())) :
    MVIViewModel<MovieDetailState, MovieDetailIntent>, ViewModel() {

    private val movieDetailProcessor = MovieDetailProcessor

    override fun processIntent(intent: MovieDetailIntent, owner: LifecycleOwner) {
        when (intent) {
            is MovieDetailIntent.getMovieDetailByMovieId -> {
                movieDetailProcessor.getMovieDetailByMovieId(intent.id)
                    .observe(owner) { newState ->
                        state.postValue(newState)
                    }
            }
        }
    }

}