package com.thurainx.themovieapplication.interactors

import androidx.lifecycle.LiveData
import com.thurainx.themovieapplication.data.models.MovieModelImpl
import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.data.vos.MovieVO
import io.reactivex.rxjava3.core.Observable

object MovieInteractorImpl: MovieInteractor {
    private val mMovieModel = MovieModelImpl

    override fun getNowPlayingMovies(onFail: (String) -> Unit): LiveData<List<MovieVO>>? {
        return mMovieModel.getNowPlayingMovies(onFail)
    }

    override fun getPopularMovies(onFail: (String) -> Unit): LiveData<List<MovieVO>>? {
        return mMovieModel.getPopularMovies(onFail)
    }

    override fun getTopRatedMovies(onFail: (String) -> Unit): LiveData<List<MovieVO>>? {
        return mMovieModel.getTopRatedMovies(onFail)
    }

    override fun getGenresList(onSuccess: (List<GenreVO>) -> Unit, onFail: (String) -> Unit) {
        mMovieModel.getGenresList(onSuccess, onFail)
    }

    override fun getMoviesByGeneres(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFail: (String) -> Unit
    ) {
        mMovieModel.getMoviesByGeneres(genreId, onSuccess, onFail)
    }

    override fun getActorList(onSuccess: (List<ActorVO>) -> Unit, onFail: (String) -> Unit) {
        mMovieModel.getActorList(onSuccess, onFail)
    }

    override fun getMovieDetailById(id: String, onFail: (String) -> Unit): LiveData<MovieVO>? {
        return mMovieModel.getMovieDetailById(id, onFail)
    }

    override fun getCreditByMovieId(
        id: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFail: (String) -> Unit
    ) {
        mMovieModel.getCreditByMovieId(id, onSuccess, onFail)
    }

    override fun searchMovies(query: String): Observable<List<MovieVO>> {
        return mMovieModel.searchMovies(query)
    }
}