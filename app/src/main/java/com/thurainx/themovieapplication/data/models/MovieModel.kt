package com.thurainx.themovieapplication.data.models

import androidx.lifecycle.LiveData
import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.network.dataagents.MovieDataAgent
import com.thurainx.themovieapplication.network.dataagents.RetrofitDataAgentImpl
import com.thurainx.themovieapplication.network.responses.CreditListByMovieResponse
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.Flow

interface MovieModel {

    // Live Data
     fun getNowPlayingMovies(
        onFail : (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getPopularMovies(
        onFail : (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getTopRatedMovies(
        onFail : (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getGenresList(
        onSuccess : (List<GenreVO>) -> Unit,
        onFail : (String) -> Unit
    )
    fun getMoviesByGeneres(
        genreId: String,
        onSuccess : (List<MovieVO>) -> Unit,
        onFail : (String) -> Unit
    )
    fun getActorList(
        onSuccess : (List<ActorVO>) -> Unit,
        onFail : (String) -> Unit
    )

    fun getMovieDetailById(
        id: String,
        onFail : (String) -> Unit
    ): LiveData<MovieVO>?

    fun getCreditByMovieId(
        id: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFail : (String) -> Unit
    )

    fun searchMovies(
        query: String,
    ): Observable<List<MovieVO>>

    // Observable
    fun getNowPlayingMoviesObservable(): Observable<List<MovieVO>>?
    fun getPopularMoviesObservable(): Observable<List<MovieVO>>?
    fun getTopRatedMoviesObservable(): Observable<List<MovieVO>>?
    fun getGenresListObservable(): Observable<List<GenreVO>>?
    fun getMoviesByGeneresObservable(genreId: String): Observable<List<MovieVO>>?
    fun getActorListObservable(): Observable<List<ActorVO>>?
    fun getMovieDetailByIdObservable(id: String): Observable<MovieVO>?
    fun getCreditByMovieIdObservable(id: String) : Observable<Pair<List<ActorVO>, List<ActorVO>>>?
}