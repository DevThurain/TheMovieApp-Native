package com.thurainx.themovieapplication.network.responses

import com.google.gson.annotations.SerializedName
import com.thurainx.themovieapplication.data.vos.DateVO
import com.thurainx.themovieapplication.data.vos.MovieVO

data class MovieListResponse(
    @SerializedName("dates")
    val dates: DateVO?,

    @SerializedName("page")
    val page: Int?,

    @SerializedName("results")
    val results: List<MovieVO>?,

    )
