package com.jshelf.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "snacks")
data class SnackVO(
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int = 0,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String?,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String?,

    @SerializedName("price")
    @ColumnInfo(name = "price")
    var price: Int = 0,

    @SerializedName("image")
    @ColumnInfo(name = "image")
    val image: String?,

    @ColumnInfo(name = "quantity")
    var quantity: Int = 0

) : Serializable
