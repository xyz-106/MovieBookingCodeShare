package com.jshelf.themoviebookingapp.activities

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.activity_final.*
import java.time.LocalDate

class FinalActivity : AppCompatActivity() {
    var mMovieTitle: String = ""
    var mMovieRunTime: Int = 0
    var mPosterPath: String = ""
    var mRow: String = ""
    var mSeatNumber: String = ""
    var mBookingDate: String = ""
    var totalPrice: String = ""
    var movieTime: String = ""
    var mBookingNumber: String = ""
    var mTheater: String = ""
    var mTotalSeat: Int = 0

    companion object {
        private const val EXTRA_MOVIE_TITLE = "EXTRA_MOVIE_TITLE"
        private const val EXTRA_MOVIE_LENGTH = "EXTRA_MOVIE_LENGTH"
        private const val EXTRA_BOOKING_NUMBER = "EXTRA_BOOKING_NUMBER"
        private const val EXTRA_SHOW_DATE = "EXTRA_SHOW_DATE"
        private const val EXTRA_MOVIE_THEATER = "EXTRA_MOVIE_THEATER"
        private const val EXTRA_SEAT = "EXTRA_SEAT"
        private const val EXTRA_ROW = "EXTRA_ROW"
        private const val EXTRA_TOTAL_PRICE = "EXTRA_TOTAL_PRICE"
        private const val EXTRA_MOVIE_TIME = "EXTRA_MOVIE_TIME"
        private const val EXTRA_POSTER_PATH = "EXTRA_POSTER_PATH"
        private const val EXTRA_QR_CODE = "EXTRA_QR_CODE"
        private const val EXTRA_TOTAL_SEAT = "EXTRA_TOTAL_SEAT"
        fun newIntent(
            context: Context,
            movieTitle: String,
            movieLength: Int,
            bookingNumber: String,
            movieDate: String,
            movieTheater: String,
            cinemaSeat: String,
            cinemaRow: String,
            totalPrice: String,
            movieTime: String,
            posterPath: String,
            totalSeat: Int
        ): Intent {
            val intent = Intent(context, FinalActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_LENGTH, movieLength)
            intent.putExtra(EXTRA_MOVIE_TITLE, movieTitle)
            intent.putExtra(EXTRA_BOOKING_NUMBER, bookingNumber)
            intent.putExtra(EXTRA_SHOW_DATE, movieDate)
            intent.putExtra(EXTRA_MOVIE_THEATER, movieTheater)
            intent.putExtra(EXTRA_SEAT, cinemaSeat)
            intent.putExtra(EXTRA_ROW, cinemaRow)
            intent.putExtra(EXTRA_TOTAL_PRICE, totalPrice)
            intent.putExtra(EXTRA_MOVIE_TIME, movieTime)
            intent.putExtra(EXTRA_POSTER_PATH, posterPath)
            intent.putExtra(EXTRA_TOTAL_SEAT, totalSeat)
            return intent
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        setUpBackPress()
        setUpView()
        bindData()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun bindData() {

        Glide.with(this).load("$IMAGE_BASE_URL${mPosterPath}")
            .into(imgFinal)
        tvFinalMovieTitle.text = mMovieTitle
        tvFinalMovieLength.text = mMovieRunTime.toString() + " mins"
        tvFinalBookingNo.text = mBookingNumber
        tvFinalCinemaTheater.text = "Cinema " + mTheater
        tvFinalRow.text = mRow
        tvFinalSeat.text = mSeatNumber
        tvFinalPrice.text = totalPrice
        tvFinalSeen.text = mTotalSeat.toString()
        val changeFormat = LocalDate.parse(mBookingDate)
        val str =
            movieTime + "-" + changeFormat.dayOfMonth.toString() + " " + changeFormat.month.toString()
                .take(3)
        tvFinalShowDateAndTime.text = str
        val text = tvFinalBookingNo.text.toString()
        val bitMatrix = createBitmatrix(text)
        val pixels = setBitmapPixels(bitMatrix)
        val bitmap = encodeBitmap(pixels, bitMatrix.width, bitMatrix.height)
        imgFinalBarcode.setImageBitmap(bitmap)

    }

    private fun setUpView() {
        mMovieTitle = intent.getStringExtra(EXTRA_MOVIE_TITLE).toString()
        mMovieRunTime = intent.getIntExtra(EXTRA_MOVIE_LENGTH, 0)
        mPosterPath = intent.getStringExtra(EXTRA_POSTER_PATH).toString()
        mRow = intent.getStringExtra(EXTRA_ROW).toString()
        mSeatNumber = intent.getStringExtra(EXTRA_SEAT).toString()
        mBookingDate = intent.getStringExtra(EXTRA_SHOW_DATE).toString()
        movieTime = intent.getStringExtra(EXTRA_MOVIE_TIME).toString()
        totalPrice = intent.getStringExtra(EXTRA_TOTAL_PRICE).toString()
        mBookingNumber = intent.getStringExtra(EXTRA_BOOKING_NUMBER).toString()
        mTheater = intent.getStringExtra(EXTRA_MOVIE_THEATER).toString()
        mTotalSeat = intent.getIntExtra(EXTRA_TOTAL_SEAT, 0)
    }

    private fun setUpBackPress() {
        backFromFinal.setOnClickListener {
            startActivity(HomeActivity.newIntent(this))

        }
    }

    private fun createBitmatrix(urlString: String) =
        MultiFormatWriter().encode(urlString, BarcodeFormat.CODE_128, 340, 80)

    private fun setBitmapPixels(bitMatrix: BitMatrix): IntArray {
        val pixels = IntArray(bitMatrix.width * bitMatrix.height)

        for (y in 0 until bitMatrix.height) {
            val offset = y * bitMatrix.width
            for (x in 0 until bitMatrix.width)
                pixels[offset + x] = if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE
        }
        return pixels
    }

    private fun encodeBitmap(pixels: IntArray, width: Int, height: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }

    override fun onBackPressed() {
        startActivity(HomeActivity.newIntent(this))
    }
}