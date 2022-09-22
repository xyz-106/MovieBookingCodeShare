package com.jshelf.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class SnackCheckOutResultVO(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    val name: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("price")
    val price: Int = 0,

    @SerializedName("image")
    val image: String?,

    @SerializedName("unit_price")
    val unitPrice: Int = 0,

    @SerializedName("quantity")
    val quantity: Int = 0,

    @SerializedName("total_price")
    val totalPrice:Int=0

)