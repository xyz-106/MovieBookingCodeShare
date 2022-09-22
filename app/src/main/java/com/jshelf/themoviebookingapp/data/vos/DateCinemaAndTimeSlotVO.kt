package com.jshelf.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jshelf.themoviebookingapp.persistence.typeconverters.MovieCinemaVOTypeConverter

@Entity(tableName = "dateCinemaAndTimeSlots")
@TypeConverters(
    MovieCinemaVOTypeConverter::class
)
data class DateCinemaAndTimeSlotVO(
    @PrimaryKey
    @ColumnInfo(name = "date")
    var date: String = "",

    @ColumnInfo(name = "cinemas")
    var cinemas: List<MovieCinemaVO>? = null
)
