package com.jshelf.themoviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jshelf.themoviebookingapp.data.vos.DateCinemaAndTimeSlotVO

@Dao
interface CinemaAndTimeSlotDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCinemaDateAndTime(dateCinemaAndTimeSlot: DateCinemaAndTimeSlotVO)

    @Query("SELECT * FROM dateCinemaAndTimeSlots WHERE date=:date")
    fun getCinemaDateAndTime(date: String): DateCinemaAndTimeSlotVO



}