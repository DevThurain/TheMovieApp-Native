package com.thurainx.themovieapplication.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thurainx.themovieapplication.data.vos.ActorVO

class ActorTypeConverter {
    @TypeConverter
    fun toString(actorList: List<ActorVO>?): String {
        return Gson().toJson(actorList)
    }

    @TypeConverter
    fun toActorList(jsonStr: String) : List<ActorVO>?{
        val type = object : TypeToken<List<ActorVO>?>(){}.type
        return Gson().fromJson(jsonStr, type)
    }
}