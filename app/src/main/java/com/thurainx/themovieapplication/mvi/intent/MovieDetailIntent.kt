package com.thurainx.themovieapplication.mvi.intent

import com.thurainx.themovieapplication.mvi.mviBased.MVIIntent

sealed class MovieDetailIntent : MVIIntent{
    class getMovieDetailByMovieId(val id: Int) : MovieDetailIntent()
}