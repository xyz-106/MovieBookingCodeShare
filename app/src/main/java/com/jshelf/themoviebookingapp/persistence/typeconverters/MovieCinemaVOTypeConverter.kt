package com.jshelf.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jshelf.themoviebookingapp.data.vos.MovieCinemaVO
import com.jshelf.themoviebookingapp.data.vos.MovieVO

class MovieCinemaVOTypeConverter {
    @TypeConverter
    fun toString(movieList: List<MovieCinemaVO>?): String {
        return Gson().toJson(movieList)
    }

    @TypeConverter
    fun toMovieCinemaList(movieCinemaListJSONString: String): List<MovieCinemaVO>? {
        val movieCinemaListType = object : TypeToken<List<MovieCinemaVO>?>() {}.type
        return Gson().fromJson(movieCinemaListJSONString, movieCinemaListType)
    }
}