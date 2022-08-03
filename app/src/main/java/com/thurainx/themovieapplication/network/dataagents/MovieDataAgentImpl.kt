package com.thurainx.themovieapplication.network.dataagents

import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.thurainx.themovieapplication.data.vos.MovieListResponse
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

val BASED_URL = "https://api.themoviedb.org/3"
val API_GET_NOW_PLAYING = "/movie/now_playing"
val MOVIE_API_KEY = "a86e2ebff9ae95b6e167546fae5fd636"
class MovieDataAgentImpl : MovieDataAgent() {
    override fun getNowPlayingMovies() {

    }

    class GetNowPlayingTask() : AsyncTask<Void, Void, MovieListResponse?>() {
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
            val url: URL
            var reader: BufferedReader? = null
            val stringBuilder: StringBuilder


            try{
                url = URL("""$BASED_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US"""")

                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "GET"
                connection.connectTimeout = 15 * 1000

                connection.doInput = true
                connection.doOutput = false

                reader = BufferedReader(InputStreamReader(connection.inputStream))

                stringBuilder = StringBuilder()

                for (line in reader.readLines()){
                    stringBuilder.append(line + "\n")
                }

                val responseString = stringBuilder.toString()
                Log.d("NowPlayingMovies", responseString)

                val movieListResponse = Gson().fromJson(responseString, MovieListResponse::class.java)

                return movieListResponse
            }catch (e: Exception){
                e.printStackTrace()
                Log.e("NowPlayingMovies",e.message ?: "")
            }finally {
                reader?.close()
            }

            return null




        }

    }
}