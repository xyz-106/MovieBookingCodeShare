package com.jshelf.themoviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class CheckOutVO(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("booking_no")
    val bookingNumber: String?,

    @SerializedName("booking_date")
    val bookingDate: String?,

    @SerializedName("row")
    val row: String?,

    @SerializedName("seat")
    val seat: String?,

    @SerializedName("total_seat")
    val totalSeat: Int = 0,

    @SerializedName("total")
    val total: String?,

    @SerializedName("movie_id")
    val movieId: Int = 0,

    @SerializedName("cinema_id")
    val cinemaId: Int = 0,

    @SerializedName("username")
    val userName: String?,

    @SerializedName("timeslot")
    val timeSlot: TimeSlotVO?,

    @SerializedName("snacks")
    val snacks: List<SnackCheckOutResultVO>?,

    @SerializedName("qr_code")
    val qrCode: String?
)