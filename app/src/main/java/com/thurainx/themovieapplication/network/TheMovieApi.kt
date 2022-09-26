package com.thurainx.themovieapplication.network

import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.network.responses.ActorListResponse
import com.thurainx.themovieapplication.network.responses.CreditListByMovieResponse
import com.thurainx.themovieapplication.network.responses.GenreListResponse
import com.thurainx.themovieapplication.network.responses.MovieListResponse
import com.thurainx.themovieapplication.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {
    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovieList(
        @Query(PARAM_PAGE) page: Int = 1,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_LANGUAGE) language: String = "en-US",
    ) : Observable<MovieListResponse>

    @GET(API_GET_POPULAR_MOVIES)
    fun getPopularMovieList(
        @Query(PARAM_PAGE) page: Int = 1,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_LANGUAGE) language: String = "en-US",
    ) : Observable<MovieListResponse>

    @GET(API_GET_TOP_RATED_MOVIES)
    fun getTopRatedMovieList(
        @Query(PARAM_PAGE) page: Int = 1,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_LANGUAGE) language: String = "en-US",
    ) : Observable<MovieListResponse>

    @GET(API_GET_GENRES)
    fun getGenresList(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
    ) : Observable<GenreListResponse>

    @GET(API_GET_MOVIES_BY_GENRE)
    fun getMoviesByGenre(
        @Query(PARAM_PAGE) page: Int = 1,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_LANGUAGE) language: String = "en-US",
        @Query(PARAM_GENRE_ID) genreId: String,
    ) : Observable<MovieListResponse>

    @GET(API_GET_ACTOR_LIST)
    fun getActorList(
        @Query(PARAM_PAGE) page: Int = 1,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
    ) : Observable<ActorListResponse>


    @GET(API_GET_MOVIE_DETAIL+"/{movie_id}")
    fun getMovieById(
        @Path("movie_id") movie_id: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
    ) : Observable<MovieVO>

    @GET(API_GET_MOVIE_DETAIL+"/{movie_id}/credits")
    fun getCreditByMovieId(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
    ) : Observable<CreditListByMovieResponse>
}