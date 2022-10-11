package com.thurainx.themovieapplication.mvi.processor

import androidx.lifecycle.LiveData
import androidx.lifecycle.toLiveData
import com.thurainx.themovieapplication.data.models.MovieModelImpl
import com.thurainx.themovieapplication.mvi.state.MainState
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

object MainProcessor {
    private val mMovieModel = MovieModelImpl

    fun getHomePageData(previousState: MainState) : LiveData<MainState>{
        return Observable.zip(
            mMovieModel.getNowPlayingMoviesObservable(),
            mMovieModel.getPopularMoviesObservable(),
            mMovieModel.getTopRatedMoviesObservable(),
            mMovieModel.getGenresListObservable(),
            mMovieModel.getActorListObservable()
        ){ nowPlayingMovieList, popularMovieList, topRatedMovieList, genreList, actorList ->
            return@zip previousState.copy(
                nowPlayingMovieList = nowPlayingMovieList,
                popularMovieList = popularMovieList,
                topRatedMovieList = topRatedMovieList,
                movieListByGenre = previousState.movieListByGenre,
                genreList = genreList,
                actorList = actorList,
                errorMessage = ""
            )

        }
            .toFlowable(BackpressureStrategy.BUFFER)
            .toLiveData()
    }

    fun getMovieListByGenre(genderId: Int, previousState: MainState): LiveData<MainState>{
        return mMovieModel.getMoviesByGeneresObservable(genreId = genderId.toString())
            ?.map { previousState.copy(
                nowPlayingMovieList = previousState.nowPlayingMovieList,
                popularMovieList = previousState.popularMovieList,
                topRatedMovieList = previousState.topRatedMovieList,
                movieListByGenre = it,
                genreList = previousState.genreList,
                actorList = previousState.actorList
            ) }
            ?.subscribeOn(Schedulers.io())
            ?.toFlowable(BackpressureStrategy.BUFFER)
            ?.toLiveData()!!
    }
}