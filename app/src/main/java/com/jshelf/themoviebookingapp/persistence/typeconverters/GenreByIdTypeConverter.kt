package com.jshelf.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreByIdTypeConverter {
    @TypeConverter
    fun toString(genreList: List<Int>?): String {
        return Gson().toJson(genreList)
    }

    @TypeConverter
    fun toGenreIds(genreListJSONString: String): List<Int>? {
        val genreIdsListType = object : TypeToken<List<Int>?>() {}.type
        return Gson().fromJson(genreListJSONString, genreIdsListType)
    }
}