package com.jshelf.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jshelf.themoviebookingapp.data.vos.CardVO

class CardListTypeConverter {
    @TypeConverter
    fun toString(cardList: List<CardVO>?): String {
        return Gson().toJson(cardList)
    }

    @TypeConverter
    fun toCardList(cardListJsonString: String): List<CardVO>? {
        val cardListType = object : TypeToken<List<CardVO>?>() {}.type
        return Gson().fromJson(cardListJsonString, cardListType)
    }
}