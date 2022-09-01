package com.thurainx.themovieapplication.data.vos

import com.google.gson.annotations.SerializedName

data class ProductionCompanyAndCountryVO(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("logo_path")
    val logoPath: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("origin_country")
    val originCountry: String?
)