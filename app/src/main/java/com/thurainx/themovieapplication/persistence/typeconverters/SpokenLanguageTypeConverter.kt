package com.thurainx.themovieapplication.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thurainx.themovieapplication.data.vos.SpokenLanguageVO

class SpokenLanguageTypeConverter {
    @TypeConverter
    fun toString(spokenLanguageList: List<SpokenLanguageVO>?): String {
        return Gson().toJson(spokenLanguageList)
    }

    @TypeConverter
    fun toSpokenLanguageList(jsonStr: String) : List<SpokenLanguageVO>?{
        val type = object : TypeToken<List<SpokenLanguageVO>?>(){}.type
        return Gson().fromJson(jsonStr, type)
    }
}