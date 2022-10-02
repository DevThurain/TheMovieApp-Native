package com.thurainx.themovieapplication.mvp.presenters

import com.thurainx.themovieapplication.delegates.BannerDelegate
import com.thurainx.themovieapplication.delegates.MovieDelegate
import com.thurainx.themovieapplication.delegates.ShowcaseDelegate
import com.thurainx.themovieapplication.mvp.views.MainView

interface MainPresenter : IBasePresenter, BannerDelegate, MovieDelegate, ShowcaseDelegate {
    fun initView(view: MainView)
    fun onTapGenre(genrePosition: Int)


}