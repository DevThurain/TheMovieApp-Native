package com.thurainx.themovieapplication.data.models

import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.network.dataagents.MovieDataAgent
import com.thurainx.themovieapplication.network.dataagents.RetrofitDataAgentImpl

object MovieModelImpl : MovieModel {
    private val mMovieDataAgent : MovieDataAgent = RetrofitDataAgentImpl()

    override fun getNowPlayingMovies(onSuccess: (List<MovieVO>) -> Unit, onFail: (String) -> Unit) {
        mMovieDataAgent.getNowPlayingMovies(onSuccess= onSuccess, onFail= onFail)
    }

    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFail: (String) -> Unit) {
        mMovieDataAgent.getPopularMovies(onSuccess= onSuccess, onFail= onFail)
    }

    override fun getTopRatedMovies(onSuccess: (List<MovieVO>) -> Unit, onFail: (String) -> Unit) {
        mMovieDataAgent.getTopRatedMovies(onSuccess= onSuccess, onFail= onFail)
    }

    override fun getGenresList(onSuccess: (List<GenreVO>) -> Unit, onFail: (String) -> Unit) {
        mMovieDataAgent.getGenresList(onSuccess= onSuccess, onFail= onFail)
    }

    override fun getMoviesByGeneres(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFail: (String) -> Unit
    ) {
        mMovieDataAgent.getMoviesByGenre(genreId = genreId,onSuccess= onSuccess, onFail= onFail)
    }
}