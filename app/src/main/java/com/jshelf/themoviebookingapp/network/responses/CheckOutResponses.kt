package com.jshelf.themoviebookingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.jshelf.themoviebookingapp.data.vos.CheckOutVO

data class CheckOutResponses(
    @SerializedName("code")
    val code: Int=0,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: CheckOutVO?
)