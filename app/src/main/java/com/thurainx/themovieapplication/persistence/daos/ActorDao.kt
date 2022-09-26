package com.thurainx.themovieapplication.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.utils.CAST
import com.thurainx.themovieapplication.utils.CREW
import com.thurainx.themovieapplication.utils.POPULAR

@Dao
interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleActor(actorVO: ActorVO)

    @Query("SELECT * FROM actors WHERE type = :type")
    fun getPopularActorList(type: String = POPULAR): List<ActorVO>

    @Query("DELETE FROM actors")
    fun deleteAllActors()

    @Query("SELECT * FROM actors WHERE movieId = :id AND type = :type")
    fun getActorByType(id: String, type: String) : List<ActorVO>


}