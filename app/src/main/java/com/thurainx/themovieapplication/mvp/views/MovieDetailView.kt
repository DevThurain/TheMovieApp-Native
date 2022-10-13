package com.thurainx.themovieapplication.mvp.views

import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.MovieVO

interface MovieDetailView: BasedView{
    fun showData(movieDetail: MovieVO?)
    fun showCastAndCrew(cast: List<ActorVO>, crew: List<ActorVO>)
    fun navigateBack()
}