package com.thurainx.themovieapplication.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thurainx.themovieapplication.data.vos.MovieVO
import io.reactivex.rxjava3.core.Flowable

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movieList: List<MovieVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovie(movie: MovieVO)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<MovieVO>>

    @Query("DELETE FROM movies")
    fun deleteAllMovie()

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getOneTimeMovieById(id: Int) : MovieVO?

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieByIdLiveData(id: Int) : LiveData<MovieVO>?

    @Query("SELECT * FROM movies WHERE type = :type")
    fun getMoviesByTypeLiveData(type: String) : LiveData<List<MovieVO>>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieByIdFlowable(id: Int) : Flowable<MovieVO>?

    @Query("SELECT * FROM movies WHERE type = :type")
    fun getMoviesByTypeFlowable(type: String) : Flowable<List<MovieVO>>


}