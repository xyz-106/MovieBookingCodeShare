package com.jshelf.themoviebookingapp.activities

import alirezat775.lib.carouselview.Carousel
import alirezat775.lib.carouselview.CarouselListener
import alirezat775.lib.carouselview.CarouselView
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.adapters.CarouselViewAdapter
import com.jshelf.themoviebookingapp.data.models.MovieBookingModel
import com.jshelf.themoviebookingapp.data.models.MovieBookingModelImpl
import com.jshelf.themoviebookingapp.data.vos.CardVO
import com.jshelf.themoviebookingapp.data.vos.SnackVO
import com.jshelf.themoviebookingapp.data.vos.TwoSnackVO
import com.jshelf.themoviebookingapp.data.vos.UserDataVO
import kotlinx.android.synthetic.main.activity_card_method.*
import java.io.Serializable

class CardSectionActivity : AppCompatActivity() {
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    lateinit var carouselAdapter: CarouselViewAdapter
    private var mCardList: List<CardVO>? = listOf()
    var totalPrice: Int = 0
    lateinit var carousel: Carousel
    var valueReturn: Int = 0
    var changes: Int = 0
    var mCountList: List<CardVO>? = listOf()
    var mRow: String = ""
    var mSeatNumber: String = ""
    var mBookingDate: String = ""
    var mCinemaDayTimeSlot: Int = 0
    var movieId: Int = 0
    var mCinemaId: Int = 0
    var mCardId: Int = 0
    var mSnackList: List<SnackVO> = listOf()
    var mSnackFinalList: List<TwoSnackVO>? = listOf()
    var mMovieTitle: String = ""
    var mMovieRunTime: Int = 0
    var mPosterPath: String = ""
    var mMovieTime: String = ""
    var tSize: Int = 0

    companion object {
        const val IE_DATA_TO_RETURN = "Data to return"
        private const val EXTRA_TOTAL_COST = "EXTRA_TOTAL_COST"
        private const val EXTRA_SNACK_LIST = "EXTRA_SNACK_LIST"
        private const val EXTRA_CINEMA_DAYTIME_ID = "EXTRA_CINEMA_DAYTIME_ID"
        private const val EXTRA_ROW = "EXTRA_ROW"
        private const val EXTRA_SEAT_NUMBER = "EXTRA_SEAT_NUMBER"
        private const val EXTRA_BOOKING_DATE = "EXTRA_BOOKING_DATE"
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_CINEMA_ID = "EXTRA_CINEMA_ID"
        private const val EXTRA_MOVIE_RUNTIME = "EXTRA_MOVIE_RUNTIME"
        private const val EXTRA_MOVIE_TITLE = "EXTRA_MOVIE_TITLE"
        private const val EXTRA_POSTER_PATH = "EXTRA_POSTER_PATH"
        private const val EXTRA_MOVIE_TIME = "EXTRA_MOVIE_TIME"
        fun newIntent(
            context: Context,
            totalCost: Int,
            snackList: List<SnackVO>?,
            cinemaDayTimeSlotId: Int,
            row: String,
            seatNumber: String,
            bookinDate: String,
            movieId: Int,
            cinemaId: Int,
            movieRuntime: Int,
            movieTitle: String,
            posterPath: String,
            movieTime: String
        ): Intent {
            val intent = Intent(context, CardSectionActivity::class.java)
            intent.putExtra(EXTRA_TOTAL_COST, totalCost)
            intent.putExtra(EXTRA_SNACK_LIST, snackList as Serializable)
            intent.putExtra(EXTRA_ROW, row)
            intent.putExtra(EXTRA_CINEMA_DAYTIME_ID, cinemaDayTimeSlotId)
            intent.putExtra(EXTRA_SEAT_NUMBER, seatNumber)
            intent.putExtra(EXTRA_BOOKING_DATE, bookinDate)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_CINEMA_ID, cinemaId)
            intent.putExtra(EXTRA_MOVIE_RUNTIME, movieRuntime)
            intent.putExtra(EXTRA_MOVIE_TITLE, movieTitle)
            intent.putExtra(EXTRA_POSTER_PATH, posterPath)
            intent.putExtra(EXTRA_MOVIE_TIME, movieTime)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_method)
        totalPrice = intent.getIntExtra(EXTRA_TOTAL_COST, 0)
        setUpView()
        setUpBack()
        setUpAddCard()
        setUpConfirmBtn()
        setUpCarouselAdapter()
        requestData()
    }

    private fun setUpView() {
        tvTotalCharges.text = "$" + totalPrice.toString()
        mRow = intent.getStringExtra(EXTRA_ROW).toString()
        mSeatNumber = intent.getStringExtra(EXTRA_SEAT_NUMBER).toString().dropLast(1)
        mBookingDate = intent.getStringExtra(EXTRA_BOOKING_DATE).toString()
        mCinemaDayTimeSlot = intent.getIntExtra(EXTRA_CINEMA_DAYTIME_ID, 0)
        movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        mCinemaId = intent.getIntExtra(EXTRA_CINEMA_ID, 0)
        mSnackList = intent.getSerializableExtra(EXTRA_SNACK_LIST) as List<SnackVO>
        mMovieRunTime = intent.getIntExtra(EXTRA_MOVIE_RUNTIME, 0)
        mMovieTitle = intent.getStringExtra(EXTRA_MOVIE_TITLE).toString()
        Log.d(TAG, "M Snack List" + mSnackList)
//        mSnackFinalList = mSnackList?.filter { SnackVO -> SnackVO.quantity != 0 }?.map {
//            TwoSnackVO(it.id, it.quantity)
//        }
        mSnackFinalList = mSnackList?.map {
            TwoSnackVO(it.id, it.quantity)
        }
        mPosterPath = intent.getStringExtra(EXTRA_POSTER_PATH).toString()
        mMovieTime = intent.getStringExtra(EXTRA_MOVIE_TIME).toString()
        // val person = Gson().fromJson(mSnackList, TwoSnackVO::class.java) //String to object
        //  println("Person object: $person")
        // Log.d(TAG, "Final" + person)
        //  mSnackList= person
    }


    private fun requestData() {
        mMovieBookingModel.getUserProfile(
            // getAuthorization = "Bearer $MovieBookingModelImpl.mToken",
            onSuccess = {
                Log.d(TAG, "On Success Card Section")
                cardAdd(it)
            },
            onFailure = {


            }
        )
    }

    private fun cardAdd(it: UserDataVO) {
        if (it.cards?.isNotEmpty() == true) {
            mCardList = it.cards?.reversed()
            carouselAdapter.setData(it)
            tSize = mCardList?.size ?: 0
            carousel.setCurrentPosition(tSize - 1)
            Log.d(TAG, "Card" + mCardList)
        }
    }


    private fun setUpCarouselAdapter() {

        Log.d(TAG, "currentPosition Check1 : $changes")
        carouselAdapter = CarouselViewAdapter()
        carousel = Carousel(this, carouselView, carouselAdapter)
        carousel.setOrientation(CarouselView.HORIZONTAL, true)

        carousel.addCarouselListener(object : CarouselListener {
            override fun onPositionChange(position: Int) {
                Log.d(TAG, "currentPosition Check : $position")
                mCardId = mCardList?.get(tSize - position - 1)?.id!!
                Log.d(TAG, "CARD LSIT" + mCardList?.get(tSize - position - 1)?.id)
            }

            override fun onScroll(dx: Int, dy: Int) {
            }
        })


    }


    private fun setUpConfirmBtn() {
        btnConfirmToFinal.setOnClickListener {
            requestCheckOut()
        }
    }

    private fun requestCheckOut() {

        mSnackFinalList?.let {
            mMovieBookingModel.checkOut(
                cinemaDayTimeSlotId = mCinemaDayTimeSlot,
                row = mRow,
                seatNumber = mSeatNumber,
                bookingDate = mBookingDate,
                totalPrice = totalPrice,
                movieId = movieId,
                cardId = mCardId,
                cinemaId = mCinemaId,
                snacks = it,
                onSuccess = {
                    startActivity(
                        FinalActivity.newIntent(
                            this,
                            movieTime = mMovieTime,
                            movieTitle = mMovieTitle,
                            movieLength = mMovieRunTime,
                            bookingNumber = it.bookingNumber.toString(),
                            movieDate = it.bookingDate.toString(),
                            movieTheater = it.cinemaId.toString(),
                            cinemaRow = it.row.toString(),
                            totalPrice = it.total.toString(),
                            cinemaSeat = it.seat.toString(),
                            posterPath = mPosterPath,
                            totalSeat = it.totalSeat
                        )
                    )
                },
                onFailure = {
                    Toast.makeText(this, "Failed API Call", Toast.LENGTH_LONG).show()
                    Log.d(TAG, "ON Failure card section")
                }
            )
        }

    }

    private fun setUpAddCard() {
        btnNewCard.setOnClickListener {
            val intent = Intent(this, CardPaymentDetail::class.java)
            startActivityForResult(CardPaymentDetail.newIntent(this), 100)
        }
    }


    private fun setUpBack() {
        btnBackComboset.setOnClickListener {
            super.onBackPressed()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            valueReturn = data.getIntExtra(IE_DATA_TO_RETURN, 0)
            Log.d(TAG, "Value Return" + valueReturn)
            if (valueReturn == 1) {
                requestData()
            }
        }
    }
}

