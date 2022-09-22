package com.jshelf.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jshelf.themoviebookingapp.data.vos.TimeSlotVO

class TimeSlotTypeConverter {
    @TypeConverter
    fun toString(movieTimeList: List<TimeSlotVO>?): String {
        return Gson().toJson(movieTimeList)
    }

    @TypeConverter
    fun toMovieList(movieTimeListJSONString: String): List<TimeSlotVO>? {
        val movieTimeListType = object : TypeToken<List<TimeSlotVO>?>() {}.type
        return Gson().fromJson(movieTimeListJSONString, movieTimeListType)
    }
}