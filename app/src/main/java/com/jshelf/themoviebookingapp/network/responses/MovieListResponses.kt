package com.jshelf.themoviebookingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.jshelf.themoviebookingapp.data.vos.DateVO
import com.jshelf.themoviebookingapp.data.vos.MovieVO

data class MovieListResponses (
    @SerializedName("page")
    val page: Int?,

    @SerializedName("dates")
    val dates: DateVO?,

    @SerializedName("results")
    val results: List<MovieVO>?
)