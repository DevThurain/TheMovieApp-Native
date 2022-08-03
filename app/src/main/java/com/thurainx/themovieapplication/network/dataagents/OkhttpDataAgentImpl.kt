package com.thurainx.themovieapplication.network.dataagents

import android.os.AsyncTask
import com.google.gson.Gson
import com.thurainx.themovieapplication.data.vos.MovieListResponse
import com.thurainx.themovieapplication.utils.API_GET_NOW_PLAYING
import com.thurainx.themovieapplication.utils.BASED_URL
import com.thurainx.themovieapplication.utils.MOVIE_API_KEY
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit
import kotlin.Exception


class OkhttpDataAgentImpl : MovieDataAgent() {
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    override fun getNowPlayingMovies() {
        GetOkHttpTask(okHttpClient).execute()
    }

    class GetOkHttpTask(val mClient: OkHttpClient) : AsyncTask<Void, Void, MovieListResponse?>() {
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {

            val request = Request.Builder()
                .url("""$BASED_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US"""")
                .build()

            try {
                val response = mClient.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body.let {
                        val responseString = it?.string()
                        val movieListResponse =
                            Gson().fromJson(responseString, MovieListResponse::class.java)

                        return movieListResponse
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null


        }

    }
}