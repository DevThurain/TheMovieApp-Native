package com.thurainx.themovieapplication.mvi.processor

import androidx.lifecycle.LiveData
import androidx.lifecycle.toLiveData
import com.thurainx.themovieapplication.data.models.MovieModelImpl
import com.thurainx.themovieapplication.mvi.state.MovieDetailState
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable

object MovieDetailProcessor {

    private val mMovieModel = MovieModelImpl

    fun getMovieDetailByMovieId(id: Int): LiveData<MovieDetailState> {
        return Observable.combineLatest(
            mMovieModel.getMovieDetailByIdObservable(id = id.toString()),
            mMovieModel.getCreditByMovieIdObservable(id = id.toString()),
        ) { movieVO, creditPair ->
            return@combineLatest MovieDetailState(
                movieData = movieVO,
                castList = creditPair.first,
                crewList = creditPair.second,
                errorMessage = ""
            )
        }
            .toFlowable(BackpressureStrategy.BUFFER)
            .toLiveData()
    }
}