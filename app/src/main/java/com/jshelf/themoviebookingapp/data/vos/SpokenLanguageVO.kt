package com.jshelf.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class SpokenLanguageVO(
    @SerializedName("english_name")
    val engName: String?,

    @SerializedName("iso_639_1")
    val isoName: String?,

    @SerializedName("name")
    val name: String?
)