package com.thurainx.themovieapplication.data.models

import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.network.dataagents.MovieDataAgent
import com.thurainx.themovieapplication.network.dataagents.RetrofitDataAgentImpl

interface MovieModel {

     fun getNowPlayingMovies(
        onSuccess : (List<MovieVO>) -> Unit,
        onFail : (String) -> Unit
    )
}