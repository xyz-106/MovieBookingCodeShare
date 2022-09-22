package com.jshelf.themoviebookingapp.network.dataAgents

import com.jshelf.themoviebookingapp.data.vos.*
import com.jshelf.themoviebookingapp.data.vos.CheckOutRequest

interface MovieBookingDataAgent {

    fun registerUser(
        name: String,
        email: String,
        password: String,
        phone: String,
        onSuccess: (Pair<UserDataVO?, String>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun loginUser(
        email: String,
        password: String,
        onSuccess: (Pair<UserDataVO?, String>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getUserProfile(
        userToken: String,
        onSuccess: (UserDataVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getUpComingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun logoutUser(
        userToken: String,
        onSuccess: (Int) -> Unit,
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
        authorization: String,
        onSuccess: (List<MovieCinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieSeat(
        cinemaDayTimeSlotId: Int,
        selectedDate: String,
        authorization: String,
        onSuccess: (List<List<MovieSeatSelectableVO>>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSnackList(
        authorization: String,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPaymentMethod(
        authorization: String,
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun createCard(
        authorization: String,
        cardHolder: String,
        cardNumber: String,
        expDate: String,
        cvc: String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun checkOut(
        authorization: String,
        checkOutRequest: CheckOutRequest,
        onSuccess: (CheckOutVO) -> Unit,
        onFailure: (String) -> Unit
    )
}