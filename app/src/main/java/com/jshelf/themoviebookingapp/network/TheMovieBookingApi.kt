package com.jshelf.themoviebookingapp.network

import com.jshelf.themoviebookingapp.data.vos.CheckOutRequest
import com.jshelf.themoviebookingapp.network.responses.*
import com.jshelf.themoviebookingapp.utils.*
import retrofit2.Call
import retrofit2.http.*

interface TheMovieBookingApi {
    @POST(API_REGISTER_WITH_EMAIL)
    @FormUrlEncoded
    fun registerUser(
        @Field(FIELD_NAME) name: String,
        @Field(FIELD_EMAIL) email: String,
        @Field(FIELD_PASSWORD) password: String,
        @Field(FIELD_PHONE) phone: String,
    ): Call<RegisterUserResponses>

    @POST(API_LOGIN_WITH_EMAIL)
    @FormUrlEncoded
    fun loginUser(
        @Field(FIELD_EMAIL) email: String,
        @Field(FIELD_PASSWORD) password: String,
    ): Call<RegisterUserResponses>

    @GET(API_GET_PROFILE)
    fun getUserProfile(
        @Header(PARAM_AUTHORIZATION) authorization: String
    ): Call<RegisterUserResponses>

    @POST(API_LOGOUT)
    fun logoutUser(
        @Header(PARAM_AUTHORIZATION) authorization: String
    ): Call<LogOutResponses>

    @GET(API_GET_CINEMA_DAY_TIMESLOT)
    fun getCinemaDayTime(
        @Query(PARAM_MOVIE_ID) movieId: String,
        @Query(PARAM_DATE) date: String,
        @Header(PARAM_AUTHORIZATION) authorization: String
    ): Call<CinemaTimeResponses>

    @GET(API_GET_MOVIE_SEAT)
    fun getMovieSeat(
        @Query(PARAM_CINEMA_TIME_SLOT_ID) cinemaTimeSlotId: Int,
        @Query(PARAM_SELECTED_DATE) selectedDate: String,
        @Header(PARAM_AUTHORIZATION) authorization: String
    ): Call<MovieSeatResponses>

    @GET(API_GET_SNACK_LIST)
    fun getSnackList(
        @Header(PARAM_AUTHORIZATION) authorization: String
    ): Call<SnackListResponses>

    @GET(API_GET_PAYMENT_METHOD_LIST)
    fun getPaymentMethodList(
        @Header(PARAM_AUTHORIZATION) authorization: String
    ): Call<PaymentMethodListResponses>


    @POST(API_CREATE_CARD)
    @FormUrlEncoded
    fun createCard(
        @Header(PARAM_AUTHORIZATION) authorization: String,
        @Field(PARAM_CARD_NUMBER) cardNumber: String,
        @Field(PARAM_CARD_HOLDER) cardHolder: String,
        @Field(PARAM_EXPIRE_DATE) expDate: String,
        @Field(PARAM_CVC) cvc: String
    ): Call<CreateCardResponses>


    @POST(API_CHECK_OUT)
    fun checkOut(
        @Header(PARAM_AUTHORIZATION) authorization: String,
       // @Header("Accept") accept:String="application/json",
        //@Header("Content-Type")contentType:String="application/json",
        @Body checkoutRequest: CheckOutRequest
    ):Call<CheckOutResponses>
}