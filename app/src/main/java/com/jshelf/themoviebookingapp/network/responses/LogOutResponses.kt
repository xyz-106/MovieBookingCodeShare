package com.jshelf.themoviebookingapp.network.responses

import com.google.gson.annotations.SerializedName

data class LogOutResponses(
    @SerializedName("code")
    val code: Int?,

    @SerializedName("message")
    val message: String?,
)