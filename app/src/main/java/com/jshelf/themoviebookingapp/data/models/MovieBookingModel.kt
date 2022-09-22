package com.jshelf.themoviebookingapp.data.models

import com.jshelf.themoviebookingapp.data.vos.*

interface MovieBookingModel {

    fun registerUser(
        name: String,
        email: String,
        password: String,
        phone: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun loginUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getUserProfile(
        //getAuthorization: String,
        onSuccess: (UserDataVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getUpComingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun logoutUser(
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getDateTime(
        movieId: String,
        date: String,
        onSuccess: (List<MovieCinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSeatList(
        cinemaDayTimeSlotId: Int,
        selectedDate: String,
        onSuccess: (List<List<MovieSeatSelectableVO>>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSnackList(
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPaymentList(
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun createCard(
        cardHolder: String,
        cardNumber: String,
        expDate: String,
        cvc: String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun checkOut(
        cinemaDayTimeSlotId: Int,
        row: String,
        seatNumber: String,
        bookingDate: String,
        totalPrice: Int,
        movieId: Int,
        cardId: Int,
        cinemaId: Int,
        snacks: List<TwoSnackVO>,
        //checkOutRequest: CheckOutRequest,
        onSuccess: (CheckOutVO) -> Unit,
        onFailure: (String) -> Unit
    )
}