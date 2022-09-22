package com.jshelf.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class MovieSeatSelectableVO(


    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("type")
    val type: String?,

    @SerializedName("seat_name")
    val seatName: String?,

    @SerializedName("symbol")
    val symbol: String?,

    @SerializedName("price")
    val price: Int = 0,

    var isSelected: Boolean? = false
)
