package com.jshelf.themoviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jshelf.themoviebookingapp.data.vos.ActorVO
import com.jshelf.themoviebookingapp.data.vos.UserDataVO

@Dao
interface CastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActors(actors: List<ActorVO>)

    @Query("SELECT * FROM actors")
    fun getActorsByMovieId(): List<ActorVO>

    //Login Register User Data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(userdata: UserDataVO?)

    @Query("SELECT * FROM userdata WHERE id= :id")
    fun getUserDataById(id: Int): UserDataVO?
}