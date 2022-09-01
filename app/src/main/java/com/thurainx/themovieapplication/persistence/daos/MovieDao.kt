package com.thurainx.themovieapplication.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thurainx.themovieapplication.data.vos.MovieVO

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movieList: List<MovieVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovie(movie: MovieVO)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieVO>

    @Query("DELETE FROM movies")
    fun deleteAllMovie()

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Int) : MovieVO?

    @Query("SELECT * FROM movies WHERE type = :type")
    fun getMoviesByType(type: String) : List<MovieVO>
}