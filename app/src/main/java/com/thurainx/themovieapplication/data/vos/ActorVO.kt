package com.thurainx.themovieapplication.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.thurainx.themovieapplication.persistence.typeconverters.MovieTypeConverter

@Entity(tableName = "actors")
@TypeConverters(
    MovieTypeConverter::class
)
data class ActorVO(
    @ColumnInfo(name = "id")
    @PrimaryKey
    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    val adult: Boolean?,

    @ColumnInfo(name = "gender")
    @SerializedName("gender")
    val gender: Int?,

    @ColumnInfo(name = "known_for")
    @SerializedName("known_for")
    val knownFor: List<MovieVO>?,

    @ColumnInfo(name = "known_for_department")
    @SerializedName("known_for_department")
    val knownForDepartment: String?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    val popularity: Double?,

    @ColumnInfo(name = "profile_path")
    @SerializedName("profile_path")
    val profilePath: String?,

    @ColumnInfo(name = "original_name")
    @SerializedName("original_name")
    val originalName: String?,

    @ColumnInfo(name = "character")
    @SerializedName("character")
    val character: String?,

    @ColumnInfo(name = "credit_id")
    @SerializedName("credit_id")
    val creditId: String?,

    @ColumnInfo(name = "department")
    @SerializedName("department")
    val department: String?,

    @ColumnInfo(name = "job")
    @SerializedName("job")
    val job: String?,

    @ColumnInfo(name = "type")
    @SerializedName("type")
    var type: String?,

    @ColumnInfo(name = "movieId")
    @SerializedName("movieId")
    var movieId: String?,


)