package com.thurainx.themovieapplication.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.thurainx.themovieapplication.interactors.MovieInteractorImpl
import com.thurainx.themovieapplication.mvp.views.MovieDetailView

object MovieDetailPresenterImpl: MovieDetailPresenter {
    // view
    var movieDetailView: MovieDetailView? = null
    // interactor
    val mMovieInteractor = MovieInteractorImpl

    override fun initView(view: MovieDetailView) {
        movieDetailView = view
    }

    override fun onUiReadyMovieDetail(movieId: Int, owner: LifecycleOwner) {
        mMovieInteractor.getMovieDetailById(
            id = movieId.toString(),
        ) {
            movieDetailView?.showError(it)
        }?.observe(owner) {
            movieDetailView?.showMovieData(it)
        }

        mMovieInteractor.getCreditByMovieId(
            id = movieId.toString(),
            onSuccess = {
                movieDetailView?.showCastsAndCrews(casts = it.first, crews = it.second)
            },
            onFail = {
                movieDetailView?.showError(it)
            }
        )
    }

    override fun onTapBack() {
        movieDetailView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}