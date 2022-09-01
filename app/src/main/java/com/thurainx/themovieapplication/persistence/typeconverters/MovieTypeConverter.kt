package com.thurainx.themovieapplication.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.MovieVO

class MovieTypeConverter {
    @TypeConverter
    fun toString(movieList: List<MovieVO>?): String {
        return Gson().toJson(movieList)
    }

    @TypeConverter
    fun toMovieList(jsonStr: String) : List<MovieVO>?{
        val type = object : TypeToken<List<MovieVO>?>(){}.type
        return Gson().fromJson(jsonStr, type)
    }
}