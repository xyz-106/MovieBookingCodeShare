package com.jshelf.themoviebookingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.jshelf.themoviebookingapp.data.vos.ActorVO

data class GetCreditByMovieResponses(
    @SerializedName("cast")
    val cast: List<ActorVO>,

    @SerializedName("crew")
    val crew: List<ActorVO>
)