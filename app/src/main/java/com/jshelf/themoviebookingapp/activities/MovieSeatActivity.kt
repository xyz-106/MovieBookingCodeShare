package com.jshelf.themoviebookingapp.activities

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.jshelf.themoviebookingapp.adapters.MovieSeatAdapter
import com.jshelf.themoviebookingapp.data.models.MovieBookingModel
import com.jshelf.themoviebookingapp.data.models.MovieBookingModelImpl
import com.jshelf.themoviebookingapp.data.vos.MovieSeatSelectableVO
import com.jshelf.themoviebookingapp.delegates.SeatDelegate
import com.jshelf.themoviebookingapp.R
import kotlinx.android.synthetic.main.activity_movie_seat.*
import java.time.LocalDate

class MovieSeatActivity : AppCompatActivity(), SeatDelegate {
    lateinit var mAdapter: MovieSeatAdapter
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    var mtimeSlotId: Int = 0
    var mSelectedDate: String? = ""
    private var mMovieSeatList: List<MovieSeatSelectableVO>? = null
    var mMovieCinemaName: String? = ""
    var mMovieTime: String? = ""
    var mMovieTitle: String? = ""
    var totalSeatName: String = ""
    var totalTicket: Int = 0
    var totalPrice: Int = 0
    var row: String = ""
    var mMovieId: Int = 0
    var mCinemaId: Int = 0
    var mCinemaRuntime: Int? = 0
    var mPosterPath: String? = ""

    companion object {
        private const val EXTRA_TIME_SLOT_ID = "EXTRA_TIME_SLOT_ID"
        private const val EXTRA_DATE = "EXTRA_DATE"
        private const val EXTRA_MOVIE_TITLE = "EXTRA_MOVIE_TITLE"
        private const val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"
        private const val EXTRA_TIME = "EXTRA_TIME"
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_CINEMA_ID = "EXTRA_CINEMA_ID"
        private const val EXTRA_MOVIE_RUNTIME = "EXTRA_MOVIE_RUNTIME"
        private const val EXTRA_POSTER_PATH = "EXTRA_POSTER_PATH"
        fun newIntent(
            context: Context,
            timeSlotId: Int,
            selectedDate: String,
            movieTitle: String,
            cinemaName: String,
            movieTime: String,
            movieId: Int,
            cinemaId: Int,
            cinemaRunTime: Int?,
            posterPath: String
        ): Intent {
            val intent = Intent(context, MovieSeatActivity::class.java)
            intent.putExtra(EXTRA_TIME_SLOT_ID, timeSlotId)
            intent.putExtra(EXTRA_DATE, selectedDate)
            intent.putExtra(EXTRA_MOVIE_TITLE, movieTitle)
            intent.putExtra(EXTRA_CINEMA_NAME, cinemaName)
            intent.putExtra(EXTRA_TIME, movieTime)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_CINEMA_ID, cinemaId)
            intent.putExtra(EXTRA_MOVIE_RUNTIME, cinemaRunTime)
            intent.putExtra(EXTRA_POSTER_PATH, posterPath)
            return intent
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_seat)
        setUpAdapter()
        setUpData()
        setUpView()
        setUpListener()
        requestData()

    }

    private fun setUpData() {
        mtimeSlotId = intent.getIntExtra(EXTRA_TIME_SLOT_ID, 0)
        mSelectedDate = intent?.getStringExtra(EXTRA_DATE)
        mMovieTitle = intent?.getStringExtra(EXTRA_MOVIE_TITLE)
        mMovieCinemaName = intent?.getStringExtra(EXTRA_CINEMA_NAME)
        mMovieTime = intent?.getStringExtra(EXTRA_TIME)
        mMovieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        mCinemaId = intent.getIntExtra(EXTRA_CINEMA_ID, 0)
        mCinemaRuntime = intent?.getIntExtra(EXTRA_MOVIE_RUNTIME, 0)
        mPosterPath = intent?.getStringExtra(EXTRA_POSTER_PATH)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpView() {
        tvSeatWatchTitle.text = mMovieTitle
        tvCinemaName.text = mMovieCinemaName
        val changeFormat = LocalDate.parse(mSelectedDate)
        val str =
            changeFormat.dayOfWeek.toString() + ", " + changeFormat.dayOfMonth + " " + changeFormat.month + ", " + mMovieTime
        tvShowDate.text = str

    }

    private fun requestData() {
        mMovieBookingModel.getSeatList(
            cinemaDayTimeSlotId = mtimeSlotId,
            selectedDate = mSelectedDate.toString(),
            onSuccess = {
                mMovieSeatList = it.flatten()
                mAdapter.setData(mMovieSeatList ?: listOf())

            },
            onFailure = {
            }
        )
    }


    private fun setUpListener() {
        btnMovieSeating.setOnClickListener {
            startActivity(
                mCinemaRuntime?.let { it1 ->
                    ComboSetActivity.newIntent(
                        this,
                        seatCost = totalPrice,
                        seatNumber = totalSeatName,
                        row = row,
                        bookingDate = mSelectedDate.toString(),
                        cinemaDayTimeSlot = mtimeSlotId,
                        movieId = mMovieId,
                        cinemaId = mCinemaId,
                        cinemaRuntime = it1.toInt(),
                        movieTitle = mMovieTitle.toString(),
                        posterPath = mPosterPath.toString(),
                        movieTime = mMovieTime.toString()
                    )
                }
            )
        }

        leftArrow.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpAdapter() {
        mAdapter = MovieSeatAdapter(this)
        rcSeatingPlan.adapter = mAdapter
        rcSeatingPlan.layoutManager = GridLayoutManager(applicationContext, 14)

        //   mAdapter.setNewData(DUMMY_SEATS)
    }

    override fun onTapSeat(seatName: String) {
        mMovieSeatList?.forEach {
            if (it.seatName == seatName && it.type == "available") {
                if (it.isSelected == true) {
                    it.isSelected = false
                    totalTicket--
                    totalSeatName = totalSeatName.replace(seatName + ",", "")
                    totalPrice -= it.price
                    row = it.symbol.toString()
                } else {
                    it.isSelected = true
                    totalTicket++
                    totalSeatName = "$totalSeatName$seatName,"
                    totalPrice += it.price
                    row = it.symbol.toString()
                }

            }
        }

        mAdapter.setData(mMovieSeatList ?: listOf())
        updateUI()
    }

    private fun updateUI() {
        tvRowCount.text = totalSeatName.dropLast(1)
        tvTicket.text = totalTicket.toString()
        btnMovieSeating.text = "Buy Ticket for $$totalPrice"
    }
}