package com.thurainx.themovieapplication.mvi.state

import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.mvi.mviBased.MVIState

data class MainState(
    val nowPlayingMovieList: List<MovieVO>,
    val popularMovieList: List<MovieVO>,
    val topRatedMovieList: List<MovieVO>,
    val genreList: List<GenreVO>,
    val movieListByGenre: List<MovieVO>,
    val actorList: List<ActorVO>
) : MVIState{
    companion object{
        fun idle(): MainState = MainState(
            nowPlayingMovieList = listOf(),
            popularMovieList = listOf(),
            topRatedMovieList = listOf(),
            genreList = listOf(),
            movieListByGenre = listOf(),
            actorList = listOf()
        )
    }
}
