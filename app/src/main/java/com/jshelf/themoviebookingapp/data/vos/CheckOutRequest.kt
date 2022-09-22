package com.jshelf.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class CheckOutRequest(
    @SerializedName("cinema_day_timeslot_id")
    val cinemaDayTime: Int = 0,

    @SerializedName("row")
    val row: String?,

    @SerializedName("seat_number")
    val seatNumber: String?,

    @SerializedName("booking_date")
    val bookingData: String?,

    @SerializedName("total_price")
    val totalPrice: Int = 0,

    @SerializedName("movie_id")
    val movieId: Int = 0,

    @SerializedName("card_id")
    val cardId: Int = 0,

    @SerializedName("cinema_id")
    val cinemaId: Int = 0,

    @SerializedName("snacks")
    val snacks: List<TwoSnackVO>?
)