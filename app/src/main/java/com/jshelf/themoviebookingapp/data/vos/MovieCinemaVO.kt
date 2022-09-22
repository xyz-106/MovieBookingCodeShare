package com.jshelf.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.jshelf.themoviebookingapp.persistence.typeconverters.TimeSlotTypeConverter

@Entity(tableName = "cinematheater")
@TypeConverters(
    TimeSlotTypeConverter::class
)
data class MovieCinemaVO(
    @ColumnInfo(name = "date")
    var date: String?,

    @SerializedName("cinema_id")
    @ColumnInfo(name = "cinema_id")
    val cinemaId: Int = 0,

    @SerializedName("cinema")
    @ColumnInfo(name = "cinema")
    val cinema: String?,

    @SerializedName("timeslots")
    @ColumnInfo(name = "timeslots")
    @PrimaryKey
    val timeSlots: List<TimeSlotVO> = listOf(),


    )