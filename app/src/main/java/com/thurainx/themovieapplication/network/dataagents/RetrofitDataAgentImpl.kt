package com.thurainx.themovieapplication.network.dataagents

import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.thurainx.themovieapplication.data.vos.GenreListResponse
import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.data.vos.MovieListResponse
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.network.TheMovieApi
import com.thurainx.themovieapplication.utils.BASED_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.Exception


class RetrofitDataAgentImpl : MovieDataAgent {

    private var mTheMovieApi : TheMovieApi? = null

    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofitClient = Retrofit.Builder()
            .baseUrl(BASED_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mTheMovieApi = retrofitClient.create(TheMovieApi::class.java)

    }



    override fun getNowPlayingMovies(onSuccess: (List<MovieVO>) -> Unit, onFail: (String) -> Unit) {
        mTheMovieApi?.getNowPlayingMovieList()?.enqueue(
            object : Callback<MovieListResponse>{
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {

                    val movieList = response.body()?.results ?: listOf()
//                    Log.d("movieList", movieList.toString())
                    onSuccess(movieList)

                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    t.printStackTrace()
                    onFail(t.message ?: "unknown error")
                }

            }
        )
    }

    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFail: (String) -> Unit) {
        mTheMovieApi?.getPopularMovieList()?.enqueue(
            object : Callback<MovieListResponse>{
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {

                    val movieList = response.body()?.results ?: listOf()
//                    Log.d("movieList", movieList.toString())
                    onSuccess(movieList)

                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    t.printStackTrace()
                    onFail(t.message ?: "unknown error")
                }

            }
        )
    }

    override fun getTopRatedMovies(onSuccess: (List<MovieVO>) -> Unit, onFail: (String) -> Unit) {
        mTheMovieApi?.getTopRatedMovieList()?.enqueue(
            object : Callback<MovieListResponse>{
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {

                    val movieList = response.body()?.results ?: listOf()
//                    Log.d("movieList", movieList.toString())
                    onSuccess(movieList)

                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    t.printStackTrace()
                    onFail(t.message ?: "unknown error")
                }

            }
        )
    }

    override fun getGenresList(onSuccess: (List<GenreVO>) -> Unit, onFail: (String) -> Unit) {
        mTheMovieApi?.getGenresList()?.enqueue(
            object : Callback<GenreListResponse>{
                override fun onResponse(
                    call: Call<GenreListResponse>,
                    response: Response<GenreListResponse>
                ) {

                    val genresList = response.body()?.genres ?: listOf()
//                    Log.d("movieList", movieList.toString())
                    onSuccess(genresList)

                }

                override fun onFailure(call: Call<GenreListResponse>, t: Throwable) {
                    t.printStackTrace()
                    onFail(t.message ?: "unknown error")
                }

            }
        )
    }

    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFail: (String) -> Unit
    ) {
        mTheMovieApi?.getMoviesByGenre(genreId = genreId)?.enqueue(
            object : Callback<MovieListResponse>{
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {

                    val movieList = response.body()?.results ?: listOf()
                    Log.d("movieList", movieList.toString())
                    onSuccess(movieList)

                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    t.printStackTrace()
                    onFail(t.message ?: "unknown error")
                }

            }
        )
    }


}