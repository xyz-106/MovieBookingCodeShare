package com.jshelf.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class DateVO (
    @SerializedName("maximum")
    val maximum: String?,  //network data no need to change use val
    @SerializedName("minimum")
    val minimum: String?
        )