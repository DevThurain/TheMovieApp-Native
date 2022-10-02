package com.thurainx.themovieapplication.mvp.views

import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.data.vos.MovieVO

interface MainView : BasedView{
    fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>)
    fun showPopularMovies(popularMovies: List<MovieVO>)
    fun showTopRatedMovie(topRatedMovies: List<MovieVO>)
    fun showGenres(genreList: List<GenreVO>)
    fun showMoviesByGenre(movieList: List<MovieVO>)
    fun showActors(actorList: List<ActorVO>)
    fun navigateToMovieDetail(movieId: Int)
}