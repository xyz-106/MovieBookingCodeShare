package com.jshelf.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "timeslotbycinema")
data class TimeSlotVO(
    @SerializedName("cinema_day_timeslot_id")
    @ColumnInfo(name = "cinema_day_timeslot_id")
    @PrimaryKey
    val cinemaDayTimeId: Int = 0,

    @SerializedName("start_time")
    @ColumnInfo(name = "start_time")
    val startTime: String?,

    @ColumnInfo(name = "isSelected")
    var isSelected: Boolean? = null,

    @ColumnInfo(name = "date")
    var date: String?
)