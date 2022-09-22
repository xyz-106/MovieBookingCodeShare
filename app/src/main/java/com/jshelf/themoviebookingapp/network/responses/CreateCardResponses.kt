package com.jshelf.themoviebookingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.jshelf.themoviebookingapp.data.vos.CardVO

data class CreateCardResponses(
    @SerializedName("code")
    val code: Int?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: List<CardVO>?
)