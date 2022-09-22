package com.jshelf.themoviebookingapp.activities

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.adapters.CombosetAdapter
import com.jshelf.themoviebookingapp.adapters.PaymentAdapter
import com.jshelf.themoviebookingapp.data.models.MovieBookingModel
import com.jshelf.themoviebookingapp.data.models.MovieBookingModelImpl
import com.jshelf.themoviebookingapp.data.vos.PaymentMethodVO
import com.jshelf.themoviebookingapp.data.vos.SnackVO
import com.jshelf.themoviebookingapp.data.vos.TwoSnackVO
import com.jshelf.themoviebookingapp.delegates.ComboSetDelegate
import com.jshelf.themoviebookingapp.delegates.PaymentMethodDelegate
import kotlinx.android.synthetic.main.activity_comboset.*

class ComboSetActivity : AppCompatActivity(), PaymentMethodDelegate, ComboSetDelegate {
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    lateinit var combosetAdapter: CombosetAdapter
    lateinit var paymentAdapter: PaymentAdapter
    private var mPaymentList: List<PaymentMethodVO> = listOf()
    var mCombosetList: List<SnackVO>? = listOf()
    var mtotalPrice: Int = 0
    var mRow: String = ""
    var mSeatNumber: String = ""
    var mBookingDate: String = ""
    var mCinemaDayTimeSlot: Int = 0
    var movieId: Int = 0
    var mCinemaId: Int = 0
    var jsonString: String = ""
    var mMovieTitle: String = ""
    var mMovieLength: Int? = 0
    var mPosterPath: String? = ""
    var mMovieTime: String? = ""

    companion object {
        private const val EXTRA_SEAT_COST = "EXTRA_SEAT_COST"
        private const val EXTRA_ROW = "EXTRA_ROW"
        private const val EXTRA_TIME_SLOT_ID = "EXTRA_TIME_SLOT_ID"
        private const val EXTRA_SEAT_NUMBER = "EXTRA_SEAT_NUMBER"
        private const val EXTRA_BOOKING_DATE = "EXTRA_BOOKING_DATE"
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_CINEMA_ID = "EXTRA_CINEMA_ID"
        private const val EXTRA_CINEMA_RUNTIME = "EXTRA_CINEMA_RUNTIME"
        private const val EXTRA_MOVIE_TITLE = "EXTRA_MOVIE_TITLE"
        private const val EXTRA_POSTER_PATH = "EXTRA_POSTER_PATH"
        private const val EXTRA_MOVIE_TIME = "EXTRA_MOVIE_TIME"
        fun newIntent(
            context: Context,
            seatCost: Int,
            row: String,
            seatNumber: String,
            bookingDate: String,
            cinemaDayTimeSlot: Int,
            movieId: Int,
            cinemaId: Int,
            cinemaRuntime: Int,
            movieTitle: String,
            posterPath: String,
            movieTime: String
        ): Intent {
            val intent = Intent(context, ComboSetActivity::class.java)
            intent.putExtra(EXTRA_SEAT_COST, seatCost)
            intent.putExtra(EXTRA_ROW, row)
            intent.putExtra(EXTRA_SEAT_NUMBER, seatNumber)
            intent.putExtra(EXTRA_BOOKING_DATE, bookingDate)
            intent.putExtra(EXTRA_TIME_SLOT_ID, cinemaDayTimeSlot)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_CINEMA_ID, cinemaId)
            intent.putExtra(EXTRA_CINEMA_RUNTIME, cinemaRuntime)
            intent.putExtra(EXTRA_MOVIE_TITLE, movieTitle)
            intent.putExtra(EXTRA_MOVIE_TIME, movieTime)
            intent.putExtra(
                EXTRA_POSTER_PATH, posterPath
            )
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comboset)
        mtotalPrice = intent.getIntExtra(EXTRA_SEAT_COST, 0)
        Log.i(TAG, "Arrive" + mtotalPrice)
        setUpCombosetRecyclerView()
        setUpPaymentRecyclerView()
        setUpView()
        setBack()
        setUpPayment()
        requestData()

    }


    private fun setUpView() {
        btnPay.text = "Pay $$mtotalPrice"
        tvSubTotal.text = "Sub total: $mtotalPrice$"
        mRow = intent.getStringExtra(EXTRA_ROW).toString()
        mSeatNumber = intent.getStringExtra(EXTRA_SEAT_NUMBER).toString()
        mBookingDate = intent.getStringExtra(EXTRA_BOOKING_DATE).toString()
        mCinemaDayTimeSlot = intent.getIntExtra(EXTRA_TIME_SLOT_ID, 0)
        movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        mCinemaId = intent.getIntExtra(EXTRA_CINEMA_ID, 0)
        mMovieLength = intent?.getIntExtra(EXTRA_CINEMA_RUNTIME, 0)
        mMovieTitle = intent.getStringExtra(EXTRA_MOVIE_TITLE).toString()
        mPosterPath = intent.getStringExtra(EXTRA_POSTER_PATH)
        mMovieTime = intent.getStringExtra(EXTRA_MOVIE_TIME)
    }

    private fun requestData() {
        mMovieBookingModel.getSnackList(
            onSuccess = {
                mCombosetList = it
                combosetAdapter.setData(mCombosetList ?: listOf())
            },
            onFailure = {
                Log.d(TAG, "On Failure Combot Set")
            }
        )

        mMovieBookingModel.getPaymentList(
            onSuccess = {
                mPaymentList = it
                paymentAdapter.setData(mPaymentList)

            },
            onFailure = {

            }
        )
    }

    private fun setUpPayment() {

        btnPay.setOnClickListener {
//            var product = mCombosetList?.filter { SnackVO -> SnackVO.quantity != 0 }?.map {
//                TwoSnackVO(it.id, it.quantity)
//            }
            //    product=Gson().toJson(product).toString()
            startActivity(
                mMovieLength?.let { it1 ->
                    CardSectionActivity.newIntent(
                        this,
                        totalCost = mtotalPrice,
                        snackList = mCombosetList,
                        row = mRow,
                        bookinDate = mBookingDate,
                        seatNumber = mSeatNumber,
                        cinemaDayTimeSlotId = mCinemaDayTimeSlot,
                        movieId = movieId,
                        cinemaId = mCinemaId,
                        posterPath = mPosterPath.toString(),
                        movieTitle = mMovieTitle,
                        movieRuntime = it1,
                        movieTime = mMovieTime.toString()
                    )
                }
            )

        }
    }

    private fun setBack() {
        backCombo.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpPaymentRecyclerView() {
        paymentAdapter = PaymentAdapter(this)
        rcPaymethod.adapter = paymentAdapter
        rcPaymethod.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpCombosetRecyclerView() {
        combosetAdapter = CombosetAdapter(this)
        rcCombo.adapter = combosetAdapter
        rcCombo.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
    }

    override fun onTapPaymentMethod(cardId: Int) {
        Log.i(TAG, "Card Id From" + cardId)
        mPaymentList?.forEach { cardData ->
            cardData.isSelected = cardData.id == cardId

        }
        paymentAdapter.setData(mPaymentList)
    }

    override fun onTapPlusMinus(checkOperation: Int, price: Int, snackId: Int) {
        mCombosetList?.forEach { snackData ->
            if (snackData.id == snackId) {
                when (checkOperation) {
                    1 -> {
                        setUpIncrement(snackData)

                    }
                    0 -> {
                        setUpDecrement(snackData)
                    }
                }
            }
        }

        combosetAdapter.setData(mCombosetList ?: listOf())
        Log.d(TAG, "M Comboset" + mCombosetList)
        // setUpJson()
        setUpView()
    }

    private fun setUpJson() {
        var product = mCombosetList?.filter { SnackVO -> SnackVO.quantity != 0 }?.map {
            TwoSnackVO(it.id, it.quantity)
        }?.toList()
        Log.d(TAG, "Product" + product)
        // jsonString = Gson().toJson(product).drop(1).dropLast(1)
        // Log.d(TAG, "FILTER LIST" + filterList?.toList())
        // Log.d(TAG, "Hello ${jsonString::class.simpleName}")
        Log.d(TAG, "JSON String" + jsonString)
        // val person = Gson().fromJson(jsonString, TwoSnackVO::class.java)
        // Log.d(TAG,"JSON String2"+person)
        //   println("Person object: $person")
    }

    private fun setUpDecrement(snackData: SnackVO) {
        snackData.quantity--
        if (snackData.quantity < 0) {
            mtotalPrice = mtotalPrice
            snackData.quantity = 0
        } else {

            mtotalPrice -= (snackData.price)
        }

    }

    private fun setUpIncrement(snackData: SnackVO) {
        snackData.quantity++
        mtotalPrice += snackData.price

    }


}