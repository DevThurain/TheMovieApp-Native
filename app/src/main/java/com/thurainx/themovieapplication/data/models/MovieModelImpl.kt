package com.thurainx.themovieapplication.data.models

import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.network.dataagents.MovieDataAgent
import com.thurainx.themovieapplication.network.dataagents.RetrofitDataAgentImpl

object MovieModelImpl : MovieModel {
    private val mMovieDataAgent : MovieDataAgent = RetrofitDataAgentImpl()

    override fun getNowPlayingMovies(onSuccess: (List<MovieVO>) -> Unit, onFail: (String) -> Unit) {
        mMovieDataAgent.getNowPlayingMovies(onSuccess= onSuccess, onFail= onFail)
    }
}