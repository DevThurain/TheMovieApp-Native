package com.thurainx.themovieapplication.mvi.state

import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.mvi.mviBased.MVIState

data class MovieDetailState(
    val movieData: MovieVO?,
    val castList: List<ActorVO>,
    val crewList: List<ActorVO>,
    val errorMessage: String,

    ) : MVIState{
    companion object{
        fun idle() : MovieDetailState = MovieDetailState(
            movieData = null,
            castList = listOf(),
            crewList = listOf(),
            errorMessage = ""
        )
    }
}
