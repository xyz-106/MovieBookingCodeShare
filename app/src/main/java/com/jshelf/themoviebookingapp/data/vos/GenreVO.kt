package com.jshelf.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genre")
data class GenreVO(

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int=0,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String?,
)