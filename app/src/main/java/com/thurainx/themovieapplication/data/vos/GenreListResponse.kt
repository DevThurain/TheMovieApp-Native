package com.thurainx.themovieapplication.data.vos

import com.google.gson.annotations.SerializedName

data class GenreListResponse(
    @SerializedName("genres")
    val genres: List<GenreVO>?,
)