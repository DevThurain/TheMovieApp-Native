package com.thurainx.themovieapplication.mvi.intent

import com.thurainx.themovieapplication.mvi.mviBased.MVIIntent

sealed class MainIntent : MVIIntent{
    object getHomePageData : MainIntent()
    class getMovieListByGenre(val position: Int): MainIntent()
}