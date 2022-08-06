package com.thurainx.themovieapplication.data.vos

import com.google.gson.annotations.SerializedName

data class ActorVO(
    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("gender")
    val gender: Int?,

    @SerializedName("known_for")
    val knownFor: List<MovieVO>?,

    @SerializedName("known_for_department")
    val knownForDepartment: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("profile_path")
    val profilePath: String?,

)