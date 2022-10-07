package com.thurainx.themovieapplication.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.thurainx.themovieapplication.mvp.views.MovieDetailView

interface MovieDetailPresenter: IBasePresenter {
    fun initView(view: MovieDetailView)
    fun onUiReadyMovieDetail(movieId: Int, owner: LifecycleOwner)
    fun onTapBack()

}