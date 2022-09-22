package com.jshelf.themoviebookingapp.activities

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.adapters.ExternalAdapter
import com.jshelf.themoviebookingapp.adapters.MovieTimeAdapter
import com.jshelf.themoviebookingapp.data.models.ItemViewsModel
import com.jshelf.themoviebookingapp.data.models.MovieBookingModel
import com.jshelf.themoviebookingapp.data.models.MovieBookingModelImpl
import com.jshelf.themoviebookingapp.data.vos.DateTimeVO
import com.jshelf.themoviebookingapp.data.vos.MovieCinemaVO
import com.jshelf.themoviebookingapp.delegates.DateDelegate
import com.jshelf.themoviebookingapp.delegates.TimeDelegate
import kotlinx.android.synthetic.main.activity_time.*
import java.time.LocalDate

class TimeActivity : AppCompatActivity(), DateDelegate, TimeDelegate {
    lateinit var timeAdapter: MovieTimeAdapter
    lateinit var externaladapter: ExternalAdapter
    private var mApiDate: String? = null
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    private var mCinemaList: List<MovieCinemaVO>? = null
    lateinit var mDateDayList: List<DateTimeVO>
    var mMovieId: String? = null
    val data = ArrayList<ItemViewsModel>()
    val twoWeeksDate = mutableListOf<DateTimeVO>()
    var mtimeSlotId: Int = 0
    var mMovieTitle: String? = ""
    var mMovieCinema: String? = ""
    var mMovieTime: String? = ""
    var movieId: Int? = 0
    var mCinemaId: Int? = 0
    var mCinemaRuntime: Int? = 0
    var mPosterPath: String? = ""
    var onTapDateOrNot: Boolean = false
    var count:Int=14

    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_MOVIE_TITLE = "EXTRA_MOVIE_TITLE"
        private const val EXTRA_MOVIE_RUN_TIME = "EXTRA_MOVIE_RUN_TIME"
        private const val EXTRA_POSTER_PATH = "EXTRA_POSTER_PATH"
        fun newIntent(
            context: Context,
            movieId: Int,
            movieTitle: String,
            movieRunTime: Int,
            posterPath: String
        ): Intent {
            val intent = Intent(context, TimeActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_MOVIE_TITLE, movieTitle)
            intent.putExtra(EXTRA_MOVIE_RUN_TIME, movieRunTime)
            intent.putExtra(EXTRA_POSTER_PATH, posterPath)
            return intent
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)
        setUpData()
        setUpMovieTimeRecyclerView()
        setUpBackListener()
        setUpRecyclerView() //external rc
        setUpNextPage()
        requestData(mMovieId.toString(), mApiDate.toString())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpData() {
        movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        val movieTitle = intent?.getStringExtra(EXTRA_MOVIE_TITLE)
        mCinemaRuntime = intent?.getIntExtra(EXTRA_MOVIE_RUN_TIME, 0)
        mMovieTitle = movieTitle
        movieId?.let {
            mMovieId = it.toString()
        }
        mPosterPath = intent.getStringExtra(EXTRA_POSTER_PATH)
        if (mApiDate == null) {
            mApiDate = LocalDate.now().toString()
        }
    }


    private fun setUpNextPage() {

        btnNext.setOnClickListener {
            Log.d(TAG, "CHECK TIME" + mApiDate.toString())
            if (mMovieTime == "") {
                mMovieTime = "9:30 AM"
            }
            if (mtimeSlotId == 0) {
                mtimeSlotId = 1
            }
            startActivity(mtimeSlotId?.let { it1 ->
                movieId?.let { it2 ->
                    mCinemaId?.let { it3 ->
                        MovieSeatActivity.newIntent(
                            this,
                            timeSlotId = it1,
                            selectedDate = mApiDate.toString(),
                            movieTitle = mMovieTitle.toString(),
                            cinemaName = mMovieCinema.toString(),
                            movieTime = mMovieTime.toString(),
                            movieId = it2,
                            cinemaId = it3,
                            cinemaRunTime = mCinemaRuntime,
                            posterPath = mPosterPath.toString()
                        )
                    }
                }
            })

        }
    }


    private fun setUpBackListener() {
        btnBackTime.setOnClickListener {
            super.onBackPressed()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpMovieTimeRecyclerView() {
        timeAdapter = MovieTimeAdapter(this)
        generateTwoWeeksDate2()
        rcTime.adapter = timeAdapter
        rcTime.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

    }

    //Two Weeks Data
    @RequiresApi(Build.VERSION_CODES.O)
    private fun generateTwoWeeksDate2() {
        Log.d(TAG, "MOVIE ID" + mMovieId.toString())
        for (i in 1..14) {
            val date = LocalDate.now().plusDays(i - 1.toLong())
            twoWeeksDate.add(
                DateTimeVO(
                    day = date.dayOfWeek.toString().take(3),
                    date = date.toString().substring(8)
                )
            )
        }

        mDateDayList = twoWeeksDate

        timeAdapter.setData(mDateDayList)
    }

    private fun setUpRecyclerView() {
        externaladapter = ExternalAdapter(this)
        rcExternal.adapter = externaladapter
        rcExternal.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestData(movieId: String, date: String) {
        if (movieId != null) {
            mMovieBookingModel.getDateTime(
                movieId = movieId,
                date = date,
                onSuccess = {
                    Log.d(TAG, "Success")
                    mCinemaList = it
                    externaladapter.setData(it, onTapDateOrNot)

                },
                onFailure = {

                }
            )
        }
    }


    //Select Date Return
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onTapDate(dateSelected: String) {
        onTapDateOrNot = true
        Log.d(TAG, "TODAY" + dateSelected)
        for (dateData in mDateDayList) {
            dateData.isSelected = dateData.date == dateSelected
        }
        timeAdapter.setData(mDateDayList)
        mApiDate = LocalDate.now().toString().take(8).plus(dateSelected)
        requestData(mMovieId.toString(), mApiDate.toString())
    }

    override fun onTapTime(timeSlotId: Int) {

        mtimeSlotId = timeSlotId
        mCinemaList?.forEach { cinemaId ->
            cinemaId.timeSlots?.forEach { timeSlot ->
                if (timeSlot.cinemaDayTimeId == timeSlotId) {
                    mMovieCinema = cinemaId.cinema
                    mMovieTime = timeSlot.startTime
                    mCinemaId = cinemaId.cinemaId
                    timeSlot.isSelected = true

                } else {
                    timeSlot.isSelected = false

                }
            }
        }

        mCinemaList?.let {

            externaladapter.setData(it, true)

        }
    }
}