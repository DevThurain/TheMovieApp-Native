package com.thurainx.themovieapplication.data.vos

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("dates")
    val dates: DateVO?,

    @SerializedName("page")
    val page: Int?,

    @SerializedName("results")
    val results: List<MovieVO>?,

)
