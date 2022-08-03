package com.thurainx.themovieapplication.network.dataagents

import com.thurainx.themovieapplication.data.vos.MovieVO

interface MovieDataAgent {
     fun getNowPlayingMovies(
        onSuccess : (List<MovieVO>) -> Unit,
        onFail : (String) -> Unit
    )
}