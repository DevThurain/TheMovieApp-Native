package com.thurainx.themovieapplication.network.responses

import com.google.gson.annotations.SerializedName
import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.GenreVO

data class ActorListResponse(
    @SerializedName("results")
    val results: List<ActorVO>?,
)