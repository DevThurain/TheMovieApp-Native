package com.thurainx.themovieapplication.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.interactors.MovieInteractor
import com.thurainx.themovieapplication.interactors.MovieInteractorImpl
import com.thurainx.themovieapplication.mvp.views.MainView

class MainPresenterImpl : MainPresenter {
    // view
    var mainView: MainView? = null

    // interactor
    val mMovieInteractor = MovieInteractorImpl

    // state
    var mGenreList: List<GenreVO> = listOf()

    override fun initView(view: MainView) {
        mainView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mMovieInteractor.getNowPlayingMovies {
            mainView?.showError(it)
        }?.observe(owner) {
           mainView?.showNowPlayingMovies(it)
        }

        mMovieInteractor.getPopularMovies {
            mainView?.showError(it)
        }?.observe(owner) {
            mainView?.showPopularMovies(it)
        }

        mMovieInteractor.getTopRatedMovies {
            mainView?.showError(it)
        }?.observe(owner) {
            mainView?.showTopRatedMovies(it)
        }

        mMovieInteractor.getGenresList(
            onSuccess = { genresList ->
                mGenreList = genresList
                mainView?.showGenreList(mGenreList)

                mGenreList.firstOrNull()?.let {
                    getMoviesByGenres(position = 0)
                }
            },
            onFail = {
                mainView?.showError(it)
            }
        )

        mMovieInteractor.getActorList(
            onSuccess = { actorList ->
                mainView?.showActors(actorList)
            },
            onFail = {
                mainView?.showError(it)
            }
        )

    }

    override fun getMoviesByGenres(position: Int) {
        mGenreList.getOrNull(position)?.let {
            mMovieInteractor.getMoviesByGeneres(
                genreId = it.id.toString(),
                onSuccess = { movieList ->
                    mainView?.showMoviesByGenre(movieList)

                },
                onFail = {
                    mainView?.showError(it)
                }
            )
        }

    }



    override fun onTapBanner(movieId: Int?) {
        movieId?.let {
            mainView?.navigateToMovieDetail(it)
        }
    }

    override fun onTapShowcase(movieId: Int?) {
        movieId?.let {
            mainView?.navigateToMovieDetail(it)
        }
    }

    override fun onTapMovie(movieId: Int?) {
        movieId?.let {
            mainView?.navigateToMovieDetail(it)
        }
    }
}