package com.jshelf.themoviebookingapp.activities

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.data.models.MovieBookingModel
import com.jshelf.themoviebookingapp.data.models.MovieBookingModelImpl
import kotlinx.android.synthetic.main.activity_card_detail.*

class CardPaymentDetail : AppCompatActivity() {
    companion object {
        const val IE_DATA_TO_RETURN = "Data to return"

        fun newIntent(context: Context): Intent {
            return Intent(context, CardPaymentDetail::class.java)
        }
    }

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_detail)
        setUpCross()
        setUpListener()

    }

    private fun requestData() {
        val txt1 = edtCardNumber.text
        val txt2 = edtCardHolder.text.toString()
        val txt3 = edtExpirationDate.text.toString()
        val txt4 = edtCVC.text.toString()
        Log.d(TAG, "s" + txt1!!.isDigitsOnly())
        Log.d(TAG, "l" + txt1.length)
        Log.d(TAG, "s1" + !txt2.isDigitsOnly())
        val txt5 = txt3.split("/")

        if (txt1!!.isDigitsOnly() && txt1!!.length >= 9 && txt2 != "" && !txt2.isDigitsOnly() && txt3 != ""
            && txt5[0].length == 2 && txt5[1].length == 2 && txt5[0].toInt() > 0 && txt5[0].toInt() <= 12
            && txt5[1].toInt() >= 18 && txt5[1].toInt() <= 27
            && txt4 != "" && txt4.length == 3
        ) {
            mMovieBookingModel.createCard(
                cardNumber = txt1.toString(),
                cardHolder = txt2,
                expDate = txt3,
                cvc = txt4,
                onSuccess = {
                    val intent = Intent().putExtra(IE_DATA_TO_RETURN, 1)
                    setResult(RESULT_OK, intent)
                    finish()
                },
                onFailure = {
                    Toast.makeText(this, "Failed API Call", Toast.LENGTH_LONG).show()
                }
            )
        } else {
            Toast.makeText(this, "Fill Your Fields with Valid Data", Toast.LENGTH_LONG).show()
            Log.d(TAG, "Cannot enter request data")
        }
    }

    private fun setUpListener() {
        cardConfirm.setOnClickListener {
            //super.onBackPressed()
            requestData()
        }
    }

    private fun setUpCross() {
        crossBtn.setOnClickListener {
            super.onBackPressed()
        }
    }
}