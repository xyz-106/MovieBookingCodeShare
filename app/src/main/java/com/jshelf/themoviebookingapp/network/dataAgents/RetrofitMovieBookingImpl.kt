package com.jshelf.themoviebookingapp.network.dataAgents

import android.content.ContentValues.TAG
import android.util.Log
import com.jshelf.themoviebookingapp.data.vos.*
import com.jshelf.themoviebookingapp.network.responses.*
import com.jshelf.themoviebookingapp.network.TheMovieApi
import com.jshelf.themoviebookingapp.network.TheMovieBookingApi
import com.jshelf.themoviebookingapp.utils.BASE_URL
import com.jshelf.themoviebookingapp.utils.MOVIE_LIST_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitMovieBookingImpl : MovieBookingDataAgent {
    private var mTheMovieBookingApi: TheMovieBookingApi? = null
    private var mTheMovieApi: TheMovieApi? = null

    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val mOkHttpClient2 = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit2 = Retrofit.Builder()
            .baseUrl(MOVIE_LIST_BASE_URL)
            .client(mOkHttpClient2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mTheMovieBookingApi = retrofit.create(TheMovieBookingApi::class.java)
        mTheMovieApi = retrofit2.create(TheMovieApi::class.java)

    }

    override fun registerUser(
        name: String,
        email: String,
        password: String,
        phone: String,
        onSuccess: (Pair<UserDataVO?, String>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.registerUser(
            name = name,
            email = email,
            password = password,
            phone = phone,
        )?.enqueue(object : Callback<RegisterUserResponses> {
            override fun onResponse(
                call: Call<RegisterUserResponses>,
                response: Response<RegisterUserResponses>
            ) {
                if (response.isSuccessful) {

                    val code = response.body()?.code ?: 0

                    if (code == 201) {
                        val list = response.body()?.data
                        val token = response.body()?.token ?: ""
                        val pair = Pair(list, token)
                        onSuccess(pair)
                    }


                } else {
                    onFailure(response.message())
                }

            }

            override fun onFailure(call: Call<RegisterUserResponses>, t: Throwable) {

                onFailure(t.message ?: "")
            }

        })
    }

    override fun loginUser(
        email: String,
        password: String,
        onSuccess: (Pair<UserDataVO?, String>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.loginUser(

            email = email,
            password = password,

            )?.enqueue(object : Callback<RegisterUserResponses> {
            override fun onResponse(
                call: Call<RegisterUserResponses>,
                response: Response<RegisterUserResponses>
            ) {
                if (response.isSuccessful) {

                    val code = response.body()?.code ?: 0

                    if (code == 200) {
                        val list = response.body()?.data
                        val token = response.body()?.token ?: ""
                        onSuccess(Pair(list, token))
                    }


                } else {
                    onFailure(response.message())
                }

            }

            override fun onFailure(call: Call<RegisterUserResponses>, t: Throwable) {

                onFailure(t.message ?: "")
            }

        })
    }

    override fun getUserProfile(
        userToken: String,
        onSuccess: (UserDataVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getUserProfile(

            authorization = userToken

        )?.enqueue(object : Callback<RegisterUserResponses> {
            override fun onResponse(
                call: Call<RegisterUserResponses>,
                response: Response<RegisterUserResponses>
            ) {
                if (response.isSuccessful) {

                    val code = response.body()?.code ?: 0

                    if (code == 200) {
                        val list = response.body()?.data
                        list?.let { onSuccess(it) }
                    }


                } else {
                    onFailure(response.message())
                }

            }

            override fun onFailure(call: Call<RegisterUserResponses>, t: Throwable) {

                onFailure(t.message ?: "")
            }

        })
    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getNowPlayingMovies()?.enqueue(object : Callback<MovieListResponses> {
            override fun onResponse(
                call: Call<MovieListResponses>,
                response: Response<MovieListResponses>
            ) {
                if (response.isSuccessful) {
                    val movieList = response.body()?.results ?: listOf()
                    onSuccess(movieList)
                } else {
                    onFailure(response.message())
                }

            }

            override fun onFailure(call: Call<MovieListResponses>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getMovieDetail(movieId = movieId)
            ?.enqueue(object : Callback<MovieVO> {
                override fun onResponse(call: Call<MovieVO>, response: Response<MovieVO>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it)
                        }

                    } else {
                        onFailure(response.message())
                    }

                }

                override fun onFailure(call: Call<MovieVO>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getUpComingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getUpComingMovies()?.enqueue(object : Callback<MovieListResponses> {
            override fun onResponse(
                call: Call<MovieListResponses>,
                response: Response<MovieListResponses>
            ) {
                if (response.isSuccessful) {
                    val movieList = response.body()?.results ?: listOf()
                    onSuccess(movieList)
                } else {
                    onFailure(response.message())
                }

            }

            override fun onFailure(call: Call<MovieListResponses>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun logoutUser(
        userToken: String,
        onSuccess: (Int) -> Unit,
        onFailure: (String) -> Unit
    ) {

        mTheMovieBookingApi?.logoutUser(
            authorization = userToken,
        )?.enqueue(object : Callback<LogOutResponses> {


            override fun onResponse(
                call: Call<LogOutResponses>,
                response: Response<LogOutResponses>
            ) {
                if (response.isSuccessful) {

                    val code = response.body()?.code ?: 0
                    onSuccess(code)

                }
            }

            override fun onFailure(call: Call<LogOutResponses>, t: Throwable) {
                onFailure(t.message ?: "")
            }
        })
    }

    override fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getCreditByMovie(movieId = movieId) //first movieId is from dataagent getcreditfunction
            ?.enqueue(object : Callback<GetCreditByMovieResponses> {
                override fun onResponse(
                    call: Call<GetCreditByMovieResponses>,
                    response: Response<GetCreditByMovieResponses>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(Pair(it.cast ?: listOf(), it.crew ?: listOf()))
                        }

                    } else {
                        onFailure(response.message())
                    }

                }

                override fun onFailure(call: Call<GetCreditByMovieResponses>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getDateTime(
        movieId: String,
        date: String,
        authorization: String,
        onSuccess: (List<MovieCinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getCinemaDayTime(
            movieId = movieId,
            date = date,
            authorization = authorization
        )
            ?.enqueue(object : Callback<CinemaTimeResponses> {
                override fun onResponse(
                    call: Call<CinemaTimeResponses>,
                    response: Response<CinemaTimeResponses>
                ) {
                    if (response.isSuccessful) {

                        val code = response.body()?.code ?: 0

                        if (code == 200) {
//                        val list = response.body()?.data
//                        val token = response.body()?.token ?: ""
                            val movieList = response.body()?.data ?: listOf()
                            onSuccess(movieList)
                        }


                    } else {
                        onFailure(response.message())
                    }

                }

                override fun onFailure(call: Call<CinemaTimeResponses>, t: Throwable) {

                    onFailure(t.message ?: "")
                }

            }
            )
    }

    override fun getMovieSeat(
        cinemaDayTimeSlotId: Int,
        selectedDate: String,
        authorization: String,
        onSuccess: (List<List<MovieSeatSelectableVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getMovieSeat(
            selectedDate = selectedDate,
            cinemaTimeSlotId = cinemaDayTimeSlotId,
            authorization = authorization
        )
            ?.enqueue(object : Callback<MovieSeatResponses> {
                override fun onResponse(
                    call: Call<MovieSeatResponses>,
                    response: Response<MovieSeatResponses>
                ) {
                    if (response.isSuccessful) {

                        val code = response.body()?.code ?: 0

                        if (code == 200) {
//                        val list = response.body()?.data
//                        val token = response.body()?.token ?: ""
                            val seatList = response.body()?.data ?: listOf()
                            onSuccess(seatList)
                        }


                    } else {
                        onFailure(response.message())
                    }

                }

                override fun onFailure(call: Call<MovieSeatResponses>, t: Throwable) {

                    onFailure(t.message ?: "")
                }

            }
            )
    }

    override fun getSnackList(
        authorization: String,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getSnackList(

            authorization = authorization
        )
            ?.enqueue(object : Callback<SnackListResponses> {
                override fun onResponse(
                    call: Call<SnackListResponses>,
                    response: Response<SnackListResponses>
                ) {
                    if (response.isSuccessful) {

                        val code = response.body()?.code ?: 0

                        if (code == 200) {
//                        val list = response.body()?.data
//                        val token = response.body()?.token ?: ""
                            val snackList = response.body()?.data ?: listOf()
                            onSuccess(snackList)
                        }


                    } else {
                        onFailure(response.message())

                    }

                }

                override fun onFailure(call: Call<SnackListResponses>, t: Throwable) {

                    onFailure(t.message ?: "")
                }

            }
            )
    }

    override fun getPaymentMethod(
        authorization: String,
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getPaymentMethodList(

            authorization = authorization
        )
            ?.enqueue(object : Callback<PaymentMethodListResponses> {
                override fun onResponse(
                    call: Call<PaymentMethodListResponses>,
                    response: Response<PaymentMethodListResponses>
                ) {
                    if (response.isSuccessful) {

                        val code = response.body()?.code ?: 0

                        if (code == 200) {
//                        val list = response.body()?.data
//                        val token = response.body()?.token ?: ""
                            val paymentList = response.body()?.data ?: listOf()
                            onSuccess(paymentList)
                        }


                    } else {
                        onFailure(response.message())

                    }

                }

                override fun onFailure(call: Call<PaymentMethodListResponses>, t: Throwable) {

                    onFailure(t.message ?: "")
                }

            }
            )
    }

    override fun createCard(
        authorization: String,
        cardHolder: String,
        cardNumber: String,
        expDate: String,
        cvc: String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.createCard(

            authorization = authorization,
            cardHolder = cardHolder,
            cardNumber = cardNumber,
            expDate = expDate,
            cvc = cvc
        )
            ?.enqueue(object : Callback<CreateCardResponses> {
                override fun onResponse(
                    call: Call<CreateCardResponses>,
                    response: Response<CreateCardResponses>
                ) {
                    if (response.isSuccessful) {
                        val code = response.body()?.code ?: 0
                        if (code == 200) {
                            val createCardData = response.body()?.data ?: listOf()
                            onSuccess(createCardData)

                        }

                    }
                }


                override fun onFailure(call: Call<CreateCardResponses>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
            )
    }

    override fun checkOut(
        authorization: String,
        checkOutRequest: CheckOutRequest,
        onSuccess: (CheckOutVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.checkOut(

            authorization = authorization,
            checkoutRequest = checkOutRequest
        )
            ?.enqueue(object : Callback<CheckOutResponses> {
                override fun onResponse(
                    call: Call<CheckOutResponses>,
                    response: Response<CheckOutResponses>
                ) {
                    if (response.isSuccessful) {
                        val code = response.body()?.code ?: 0

                        val list = response.body()?.data
                        list?.let {

                            onSuccess(it)
                        }



                    } else {


                    }
                }


                override fun onFailure(call: Call<CheckOutResponses>, t: Throwable) {
                    onFailure(t.message ?: "Not Found")
                }

            }
            )
    }

}