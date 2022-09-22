package com.jshelf.themoviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jshelf.themoviebookingapp.data.vos.SnackVO

@Dao
interface SnackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSnack(snackList: List<SnackVO>)

    @Query("SELECT * FROM snacks")
    fun getSnackList(): List<SnackVO>
}