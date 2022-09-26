package com.thurainx.themovieapplication.data.models

import android.content.Context
import com.thurainx.themovieapplication.network.TheMovieApi
import com.thurainx.themovieapplication.persistence.MovieDatabase
import com.thurainx.themovieapplication.utils.BASED_URL
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BasedModel {
    protected var mTheMovieApi: TheMovieApi

    /* Database */
    protected var mMovieDatabase: MovieDatabase? = null


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
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        mTheMovieApi = retrofitClient.create(TheMovieApi::class.java)

    }

    fun initDatabase(context: Context) {
        MovieModelImpl.mMovieDatabase = MovieDatabase.getDBInstant(context)
    }
}