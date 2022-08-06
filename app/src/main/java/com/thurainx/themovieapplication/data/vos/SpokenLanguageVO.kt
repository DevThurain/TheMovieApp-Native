package com.thurainx.themovieapplication.data.vos

import com.google.gson.annotations.SerializedName

data class SpokenLanguageVO(
    @SerializedName("name")
    val name: String?,

    @SerializedName("english_name")
    val english_name: String?,

)