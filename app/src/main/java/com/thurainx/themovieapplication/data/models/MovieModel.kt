package com.thurainx.themovieapplication.data.models

import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.network.dataagents.MovieDataAgent
import com.thurainx.themovieapplication.network.dataagents.RetrofitDataAgentImpl

interface MovieModel {

     fun getNowPlayingMovies(
        onSuccess : (List<MovieVO>) -> Unit,
        onFail : (String) -> Unit
    )
    fun getPopularMovies(
        onSuccess : (List<MovieVO>) -> Unit,
        onFail : (String) -> Unit
    )
    fun getTopRatedMovies(
        onSuccess : (List<MovieVO>) -> Unit,
        onFail : (String) -> Unit
    )
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
}