package com.thurainx.themovieapplication.data.vos

import com.google.gson.annotations.SerializedName

data class MovieVO(
    @SerializedName("adult")
    val adult:Boolean?,

    @SerializedName("backdrop_path")
    val backdropPath:String?,

    @SerializedName("genre_ids")
    val genreIds:List<Int>?,

    @SerializedName("id")
    val id:Int?,

    @SerializedName("original_language")
    val originalLanguage:String?,

    @SerializedName("original_title")
    val originalTitle:String?,

    @SerializedName("overview")
    val overview:String?,

    @SerializedName("popularity")
    val popularity:Double?,

    @SerializedName("poster_path")
    val posterPath:String?,

    @SerializedName("release_date")
    val releaseDate:String?,

    @SerializedName("title")
    val title:String?,

    @SerializedName("video")
    val video:Boolean?,

    @SerializedName("vote_average")
    val voteAverage:Double?,

    @SerializedName("vote_count")
    val voteCount:Int?,

    @SerializedName("budget")
    val budget:Int?,

    @SerializedName("genres")
    val genres:List<GenreVO>?,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCountryVO>?,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryVO>?,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageVO>?,

){
    fun getVotingBasedOnFiveStars() : Float{
        return voteAverage?.div(2)?.toFloat() ?: 0.00f
    }

    fun getGenreListString(): String{
        return genres?.map { genreVO -> genreVO.name }?.joinToString(separator = ", ") ?: ""
    }

    fun getCountryListString(): String{
        return productionCountries?.map { country -> country.name }?.joinToString(separator = ", ") ?: ""
    }

}
