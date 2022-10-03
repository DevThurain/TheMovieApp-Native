package com.thurainx.themovieapplication.mvp.presenters

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.thurainx.themovieapplication.data.models.MovieModelImpl
import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.mvp.views.MainView

class MainPresenterImpl : ViewModel(), MainPresenter {
    // model
    private val mMovieModel = MovieModelImpl

    // view
    private var mView: MainView? = null

    // state
    private var mGenresList: List<GenreVO> = listOf()

    override fun initView(view: MainView) {
        mView = view
    }




    override fun onUiReady(owner: LifecycleOwner) {
        // now playing
        mMovieModel.getNowPlayingMovies { msg ->
            mView?.showError(msg)
        }?.observe(owner) {
            mView?.showNowPlayingMovies(nowPlayingMovies = it)
        }

        // popular
        mMovieModel.getPopularMovies { msg ->
            mView?.showError(msg)
        }?.observe(owner) {
            mView?.showPopularMovies(popularMovies = it)
        }

        // top rated
        mMovieModel.getTopRatedMovies { msg ->
            mView?.showError(msg)
        }?.observe(owner) {
            mView?.showTopRatedMovie(topRatedMovies = it)
        }

        // genre
        mMovieModel.getGenresList(
            onSuccess = { genresList ->
                mGenresList = genresList
                genresList.firstOrNull()?.let {
                    getMoviesByGenre(it.id.toString())
                }
                mView?.showGenres(genresList)

            },
            onFail = {
                mView?.showError(it)
            }
        )

        // actor
        mMovieModel.getActorList(
            onSuccess = { actorList ->
                mView?.showActors(actorList)
            },
            onFail = {
                mView?.showError(it)
            }
        )

        mView?.showError("MVP Architecture")

    }

    private fun getMoviesByGenre(genreId: String) {
        mMovieModel.getMoviesByGeneres(
            genreId = genreId,
            onSuccess = { movieList ->
                mView?.showMoviesByGenre(movieList)
            },
            onFail = {
                mView?.showError(it)
            }
        )
    }

    override fun onTapGenre(genrePosition: Int) {
       getMoviesByGenre(mGenresList[genrePosition].id.toString())
    }


    override fun onTapBanner(movieId: Int?) {
        movieId?.let {
            mView?.navigateToMovieDetail(movieId = it)
        }
    }

    override fun onTapMovie(movieId: Int?) {
        movieId?.let {
            mView?.navigateToMovieDetail(movieId = it)
        }
    }

    override fun onTapShowcase(movieId: Int?) {
        movieId?.let {
            mView?.navigateToMovieDetail(movieId = it)
        }
    }
}