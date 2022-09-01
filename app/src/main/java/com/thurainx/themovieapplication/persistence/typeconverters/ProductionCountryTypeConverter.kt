package com.thurainx.themovieapplication.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thurainx.themovieapplication.data.vos.ProductionCompanyAndCountryVO

class ProductionCountryTypeConverter {
    @TypeConverter
    fun toString(productionCompanyAndCountryList: List<ProductionCompanyAndCountryVO>?): String {
        return Gson().toJson(productionCompanyAndCountryList)
    }

    @TypeConverter
    fun toProductionCountryList(jsonStr: String) : List<ProductionCompanyAndCountryVO>?{
        val type = object : TypeToken<List<ProductionCompanyAndCountryVO>?>(){}.type
        return Gson().fromJson(jsonStr, type)
    }
}