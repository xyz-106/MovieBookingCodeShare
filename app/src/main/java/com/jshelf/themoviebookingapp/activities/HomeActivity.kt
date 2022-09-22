package com.jshelf.themoviebookingapp.activities

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.data.models.MovieBookingModel
import com.jshelf.themoviebookingapp.data.models.MovieBookingModelImpl
import com.jshelf.themoviebookingapp.delegates.MovieListViewHolderDelegate
import com.jshelf.themoviebookingapp.viewpods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), MovieListViewHolderDelegate {
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            return intent
        }
    }

    lateinit var mNowShowingViewpod: MovieListViewPod
    lateinit var mComingsoonViewpod: MovieListViewPod
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpListener()
        setUpHomeView()
        setUpToolbar()
        setUpViewPod()
        requestData()

    }

    private fun setUpHomeView() {
        var test = MovieBookingModelImpl.mMovieBookingDatabase?.userDao()
            ?.getUserData()
        tvUserName.text = test?.name
        navName.text = test?.name
        navGmail.text = test?.email
    }

    private fun setUpListener() {
        navImg.setOnClickListener {
            Snackbar.make(window.decorView, "Profile", Snackbar.LENGTH_LONG).show()
        }
        btnLogout.setOnClickListener {
            mMovieBookingModel.logoutUser(
                onSuccess = {

                    startActivity(WelcomeActivity.newIntent(this))
                },
                onFailure = {

                }
            )
        }
    }


    private fun setUpViewPod() {
        mComingsoonViewpod = viewPodMovielist as MovieListViewPod
        mComingsoonViewpod.setUpMovieListViewPod(

            titleText = getString(R.string.txt_view2), this

        )
        mNowShowingViewpod = viewPodMovielist2 as MovieListViewPod
        mNowShowingViewpod.setUpMovieListViewPod(
            titleText = getString(R.string.txt_view), this
        )
    }


    private fun setUpToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_black_24dp)
        setUpDrawer()
    }

    private fun setUpDrawer() {

        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerNavigation, R.string.draweropen, R.string.drawerclose)
        actionBarDrawerToggle?.let {
            drawerNavigation.addDrawerListener(it)
            it.syncState()
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle?.onOptionsItemSelected(item) == true) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }

    override fun onTapFromMovie(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(this, movieId = movieId))
    }

    override fun onBackPressed() {

    }
    private fun requestData() {
        // get User Profile
      //  Log.d(TAG, "Token" + MovieBookingModelImpl.mToken)
//        mMovieBookingModel.getUserProfile(
//            //  getAuthorization = "Bearer $MovieBookingModelImpl.mToken",
//            onSuccess = {
//                tvUserName.text = it?.name
//                navName.text = it?.name
//                navGmail.text = it?.email
//            },
//            onFailure = {}
//        )

        mMovieBookingModel.getNowPlayingMovies(
            onSuccess = {
                mComingsoonViewpod.setData(it)
            },
            onFailure = {}
        )

        mMovieBookingModel.getUpComingMovies(
            onSuccess = {
                mNowShowingViewpod.setUpComingData(it)
            },
            onFailure = {}
        )


    }
}