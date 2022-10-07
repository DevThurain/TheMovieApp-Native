package com.thurainx.themovieapplication.mvp.views

import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.MovieVO

interface MovieDetailView: BasedView {
    fun showMovieData(movie: MovieVO)
    fun showCastsAndCrews(casts: List<ActorVO>, crews: List<ActorVO>)
    fun navigateBack()
}