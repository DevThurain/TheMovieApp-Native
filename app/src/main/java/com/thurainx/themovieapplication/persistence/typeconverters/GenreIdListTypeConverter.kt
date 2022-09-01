package com.thurainx.themovieapplication.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdListTypeConverter {
    @TypeConverter
    fun toString(genreIdList: List<Int>?): String {
        return Gson().toJson(genreIdList)
    }

    @TypeConverter
    fun toGenreIdList(jsonStr: String) : List<Int>?{
        val type = object : TypeToken<List<Int>?>(){}.type
        return Gson().fromJson(jsonStr, type)
    }
}