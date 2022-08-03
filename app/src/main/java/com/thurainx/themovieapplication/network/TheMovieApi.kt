package com.thurainx.themovieapplication.network

import com.thurainx.themovieapplication.data.vos.MovieListResponse
import com.thurainx.themovieapplication.utils.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieApi {
    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovieList(
        @Query(PARAM_PAGE) page: Int = 1,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_LANGUAGE) language: String = "en-US",
    ) : Call<MovieListResponse>
}