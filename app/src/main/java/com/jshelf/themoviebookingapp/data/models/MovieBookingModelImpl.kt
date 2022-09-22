package com.jshelf.themoviebookingapp.data.models

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.jshelf.themoviebookingapp.data.vos.*
import com.jshelf.themoviebookingapp.network.dataAgents.MovieBookingDataAgent
import com.jshelf.themoviebookingapp.network.dataAgents.RetrofitMovieBookingImpl
import com.jshelf.themoviebookingapp.persistence.MovieBookingDatabase
import com.jshelf.themoviebookingapp.utils.BEARER_TOKEN

object MovieBookingModelImpl : MovieBookingModel {
    val mMovieBookingDataAgent: MovieBookingDataAgent = RetrofitMovieBookingImpl
    var dbToken = ""

    ///Database
    var mMovieBookingDatabase: MovieBookingDatabase? = null

    fun initDatabase(context: Context) {
        mMovieBookingDatabase = MovieBookingDatabase.getDBInstance(context)
    }

    override fun registerUser(
        name: String,
        email: String,
        password: String,
        phone: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.registerUser(
            name = name,
            email = email,
            phone = phone,
            password = password,
            onSuccess = {
                //This is in Data Layer
                val userVO = it.first
                val token = it.second

                userVO?.token = token
                mMovieBookingDatabase?.userDao()?.insertUserData(it.first)
                onSuccess() //This is go to View Layer
            },
            onFailure = onFailure
        )
    }

    override fun loginUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        mMovieBookingDataAgent.loginUser(
            email = email,
            password = password,
            onSuccess = {

                val userVO = it.first
                val token = it.second
                userVO?.token = token
                mMovieBookingDatabase?.userDao()?.insertUserData(userVO)
                //  this.mToken = token  //Need to drop
                onSuccess()
            },
            onFailure = onFailure
        )
    }

    override fun getUserProfile(
        onSuccess: (UserDataVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        dbToken = mMovieBookingDatabase?.userDao()?.getUserData()?.token.toString()
        mMovieBookingDatabase?.userDao()?.getUserData()
            ?.let { onSuccess(it) }
        mMovieBookingDataAgent.getUserProfile(
            userToken = BEARER_TOKEN + dbToken,
            onSuccess = {
                //  dbToken = mMovieBookingDatabase?.userDao()?.getUserData()?.token.toString()
                it.token = dbToken
                mMovieBookingDatabase?.userDao()?.insertUserData(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun logoutUser(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        dbToken = mMovieBookingDatabase?.userDao()?.getUserData()?.token.toString()
        mMovieBookingDataAgent.logoutUser(
            userToken = BEARER_TOKEN + dbToken,
            onSuccess = {
                if (it == 200)
                    dbToken = ""
                mMovieBookingDatabase?.userDao()?.deleteUserData()
                onSuccess()
            },
            onFailure = onFailure
        )
    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //Database
        onSuccess(
            mMovieBookingDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING) ?: listOf()
        )
        //Network
        mMovieBookingDataAgent.getNowPlayingMovies(onSuccess = {
            it.forEach { movie -> movie.type = NOW_PLAYING }
            mMovieBookingDatabase?.movieDao()?.insertMovies(it)

            onSuccess(it)
        }, onFailure = onFailure)


    }

    override fun getUpComingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //Database
        onSuccess(
            mMovieBookingDatabase?.movieDao()?.getMoviesByType(type = COMING_SOON) ?: listOf()
        )
        //Network
        mMovieBookingDataAgent.getUpComingMovies(onSuccess = {
            it.forEach { movie -> movie.type = COMING_SOON }
            mMovieBookingDatabase?.movieDao()?.insertMovies(it)
            onSuccess(it)
        }, onFailure = onFailure)
    }

    override fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //Database
        val movieFromDatabase =
            mMovieBookingDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())

        movieFromDatabase?.let {
            onSuccess(it)
        }

        //Network
        mMovieBookingDataAgent.getMovieDetail(
            movieId = movieId,
            onSuccess = {
                val movieFromDatabase =
                    mMovieBookingDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
                it.type = movieFromDatabase?.type
                mMovieBookingDatabase?.movieDao()?.insertSingleMovie(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess(
            Pair(
                mMovieBookingDatabase?.castDao()?.getActorsByMovieId(),
                mMovieBookingDatabase?.castDao()?.getActorsByMovieId()
            )
                    as Pair<List<ActorVO>, List<ActorVO>>
        )
        mMovieBookingDataAgent.getCreditByMovie(
            movieId = movieId,
            onSuccess = {
                mMovieBookingDatabase?.castDao()?.insertActors(it.first)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getDateTime(
        movieId: String,
        date: String,
        onSuccess: (List<MovieCinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        dbToken = mMovieBookingDatabase?.userDao()?.getUserData()?.token.toString()
        val fromDatabase = mMovieBookingDatabase?.cinemaAndTimeSlotDao()?.getCinemaDateAndTime(date)
        fromDatabase?.cinemas?.let { onSuccess(it) }
        mMovieBookingDataAgent.getDateTime(
            movieId = movieId,
            date = date,
            authorization = BEARER_TOKEN + dbToken,
            onSuccess = {
                val entityToSave = DateCinemaAndTimeSlotVO(date = date, cinemas = it)
                mMovieBookingDatabase?.cinemaAndTimeSlotDao()?.insertCinemaDateAndTime(entityToSave)
                onSuccess(it)

            },
            onFailure = onFailure
        )
    }


    override fun getSeatList(
        cinemaDayTimeSlotId: Int,
        selectedDate: String,
        onSuccess: (List<List<MovieSeatSelectableVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        dbToken = mMovieBookingDatabase?.userDao()?.getUserData()?.token.toString()
        mMovieBookingDataAgent.getMovieSeat(
            cinemaDayTimeSlotId = cinemaDayTimeSlotId,
            selectedDate = selectedDate,
            authorization = BEARER_TOKEN + dbToken,
            onSuccess = onSuccess,
            onFailure = onFailure,

            )

    }

    override fun getSnackList(onSuccess: (List<SnackVO>) -> Unit, onFailure: (String) -> Unit) {
        dbToken = mMovieBookingDatabase?.userDao()?.getUserData()?.token.toString()
        mMovieBookingDatabase?.snackDao()?.getSnackList()?.let { onSuccess(it) }
        mMovieBookingDataAgent.getSnackList(
            authorization = BEARER_TOKEN + dbToken,
            onSuccess = {
                mMovieBookingDatabase?.snackDao()?.insertSnack(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )

    }

    override fun getPaymentList(
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        dbToken = mMovieBookingDatabase?.userDao()?.getUserData()?.token.toString()
        mMovieBookingDatabase?.paymentDao()?.getPaymentMethodList()?.let { onSuccess(it) }
        mMovieBookingDataAgent.getPaymentMethod(
            authorization = BEARER_TOKEN + dbToken,
            onSuccess = {
                mMovieBookingDatabase?.paymentDao()?.insertPayment(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun createCard(
        cardHolder: String,
        cardNumber: String,
        expDate: String,
        cvc: String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        dbToken = mMovieBookingDatabase?.userDao()?.getUserData()?.token.toString()
        mMovieBookingDataAgent.createCard(
            authorization = BEARER_TOKEN + dbToken,
            cardHolder = cardHolder,
            cardNumber = cardNumber,
            expDate = expDate,
            cvc = cvc,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun checkOut(
        cinemaDayTimeSlotId: Int,
        row: String,
        seatNumber: String,
        bookingDate: String,
        totalPrice: Int,
        movieId: Int,
        cardId: Int,
        cinemaId: Int,
        snacks: List<TwoSnackVO>,
        //  checkOutRequest: CheckOutRequest,
        onSuccess: (CheckOutVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        dbToken = mMovieBookingDatabase?.userDao()?.getUserData()?.token.toString()
        var requestList = CheckOutRequest(
            cinemaDayTimeSlotId,
            row,
            seatNumber,
            bookingDate,
            totalPrice,
            movieId,
            cardId,
            cinemaId,
            snacks
        )
        Log.d(TAG, "REQUEST " + requestList)
        mMovieBookingDataAgent.checkOut(
            authorization = BEARER_TOKEN + dbToken,
            checkOutRequest = requestList,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}