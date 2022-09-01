package com.thurainx.themovieapplication.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thurainx.themovieapplication.data.vos.GenreVO

class GenreListTypeConverter {
    @TypeConverter
    fun toString(genreList: List<GenreVO>?): String {
        return Gson().toJson(genreList)
    }

    @TypeConverter
    fun toGenreList(jsonStr: String) : List<GenreVO>?{
        val type = object : TypeToken<List<GenreVO>?>(){}.type
        return Gson().fromJson(jsonStr, type)
    }
}