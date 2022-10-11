package com.thurainx.themovieapplication.mvi.intent

sealed class MainIntent {
    object GetAllData : MainIntent()
    class GetMovieListByGenreId(position: Int): MainIntent()
}