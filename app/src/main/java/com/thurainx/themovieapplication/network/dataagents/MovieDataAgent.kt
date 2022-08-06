package com.thurainx.themovieapplication.network.dataagents

import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.data.vos.MovieVO

interface MovieDataAgent {
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
    fun getMoviesByGenre(
        genreId : String,
        onSuccess : (List<MovieVO>) -> Unit,
        onFail : (String) -> Unit
    )
    fun getActorList(
        onSuccess : (List<ActorVO>) -> Unit,
        onFail : (String) -> Unit
    )
}