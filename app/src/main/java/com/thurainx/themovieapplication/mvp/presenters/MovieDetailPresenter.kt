package com.thurainx.themovieapplication.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.thurainx.themovieapplication.mvp.views.MovieDetailView

interface MovieDetailPresenter{
    fun initView(view: MovieDetailView)
    fun onUiReady(movieId: Int, owner: LifecycleOwner)
    fun onTapBack()
}