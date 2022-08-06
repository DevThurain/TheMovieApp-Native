package com.thurainx.themovieapplication.network.responses

import com.google.gson.annotations.SerializedName
import com.thurainx.themovieapplication.data.vos.GenreVO

data class GenreListResponse(
    @SerializedName("genres")
    val genres: List<GenreVO>?,
)