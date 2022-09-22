package com.jshelf.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jshelf.themoviebookingapp.data.vos.MovieVO

class MovieListTypeConverter {
    @TypeConverter
    fun toString(movieList: List<MovieVO>?): String {
        return Gson().toJson(movieList)
    }

    @TypeConverter
    fun toMovieList(movieListJSONString: String): List<MovieVO>? {
        val movieListType = object : TypeToken<List<MovieVO>?>() {}.type
        return Gson().fromJson(movieListJSONString, movieListType)
    }
}