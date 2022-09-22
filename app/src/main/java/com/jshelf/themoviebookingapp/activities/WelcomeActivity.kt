package com.jshelf.themoviebookingapp.activities

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.adapters.DifferentScreenViewPagerAdapter
import com.jshelf.themoviebookingapp.data.models.MovieBookingModel
import com.jshelf.themoviebookingapp.data.models.MovieBookingModelImpl
import com.jshelf.themoviebookingapp.delegates.PassDataDelegate
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_login.*

class WelcomeActivity : AppCompatActivity(), PassDataDelegate {
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, WelcomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpLoginTabLayout()
        //  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    private fun setUpLoginTabLayout() {
        val differentScreensAdapter = DifferentScreenViewPagerAdapter(this, this)
        viewPagerDifferentScreen.adapter = differentScreensAdapter

        TabLayoutMediator(tabLayoutLogin, viewPagerDifferentScreen) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.login)
                }
                else -> {
                    tab.text = getString(R.string.signup)
                }
            }
        }.attach()
    }


    override fun onPassSignUpData(email: String, password: String, name: String, phone: String) {
        mMovieBookingModel.registerUser(

            name = name,
            email = email,
            password = password,
            phone = phone,
            onSuccess = {

                startActivity(HomeActivity.newIntent(this))
            },
            onFailure = {

            }
        )
    }

    override fun onPassLoginData(email: String, password: String) {

        if (email.isEmpty() && password.isEmpty()) {
            test.visibility = View.VISIBLE
            test2.visibility = View.VISIBLE
        }
        mMovieBookingModel.loginUser(
            email = email,
            password = password,
            onSuccess = {
                test.visibility = View.INVISIBLE
                test2.visibility = View.INVISIBLE
                startActivity(HomeActivity.newIntent(this))
            },
            onFailure = {
                checkField(email, password)
                val errorMessage = "$it email"
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()

            }
        )
    }

    private fun checkField(email: String, password: String) {
        if (email.isNotEmpty() && password.isEmpty()) {
            test.visibility = View.VISIBLE
            test.text = "Please Check Your Email"
            test2.visibility = View.VISIBLE
            test2.text = "Please Fill Your Password"
        } else if (email.isEmpty() && password.isNotEmpty()) {
            test2.visibility = View.VISIBLE
            test2.text = "Please Check Your Password"
            test.visibility = View.VISIBLE
            test.text = "Please Fill Your Email"
        } else if (email.isNotEmpty() && password.isNotEmpty()) {
            test.visibility = View.VISIBLE
            test2.visibility = View.VISIBLE
            test.text = "Please Check Your Email"
            test2.text = "Please Check Your Password"
        }
    }


}


