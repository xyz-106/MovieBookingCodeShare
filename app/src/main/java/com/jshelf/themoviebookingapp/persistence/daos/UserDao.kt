package com.jshelf.themoviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jshelf.themoviebookingapp.data.vos.UserDataVO

@Dao
interface UserDao {
    //Login Register User Data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(userdata: UserDataVO?)

    @Query("SELECT * FROM userdata")
    fun getUserData(): UserDataVO?

    @Query("DELETE FROM userdata")
    fun deleteUserData()
}