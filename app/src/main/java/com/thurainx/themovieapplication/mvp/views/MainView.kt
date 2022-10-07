package com.thurainx.themovieapplication.mvp.views

import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.data.vos.MovieVO

interface MainView: BasedView {
    fun showNowPlayingMovies(movieList: List<MovieVO>)
    fun showPopularMovies(movieList: List<MovieVO>)
    fun showTopRatedMovies(movieList: List<MovieVO>)
    fun showGenreList(genreList: List<GenreVO>)
    fun showMoviesByGenre(movieList: List<MovieVO>)
    fun showActors(actorList: List<ActorVO>)
    fun navigateToMovieDetailScreen(movieId: Int)
}