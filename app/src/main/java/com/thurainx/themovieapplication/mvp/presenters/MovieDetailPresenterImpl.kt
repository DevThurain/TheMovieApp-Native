package com.thurainx.themovieapplication.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.thurainx.themovieapplication.data.models.MovieModelImpl
import com.thurainx.themovieapplication.mvp.views.MovieDetailView

class MovieDetailPresenterImpl: ViewModel(), MovieDetailPresenter {
    // model
    val mMovieModel = MovieModelImpl

    // view
    var mView: MovieDetailView? = null

    override fun initView(view: MovieDetailView) {
        mView = view
    }

    override fun onUiReady(movieId: Int, owner: LifecycleOwner) {
        // bindData
        mMovieModel.getMovieDetailById(
            id = movieId.toString(),
        ) {
            mView?.showError(it)
        }?.observe(owner) {
            it
            mView?.showData(movieDetail = it)
        }

        // cast & crew
        mMovieModel.getCreditByMovieId(
            id = movieId.toString(),
            onSuccess = {
               mView?.showCastAndCrew(cast = it.first,crew = it.second)
            },
            onFail = {
                mView?.showError(it)
            }
        )
    }

    override fun onTapBack() {
        mView?.navigateBack()
    }
}