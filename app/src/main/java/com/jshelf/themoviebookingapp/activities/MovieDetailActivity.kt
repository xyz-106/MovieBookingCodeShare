package com.jshelf.themoviebookingapp.activities

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.jshelf.themoviebookingapp.adapters.ButtonAdapter
import com.jshelf.themoviebookingapp.adapters.CastAdapter
import com.jshelf.themoviebookingapp.data.models.MovieBookingModel
import com.jshelf.themoviebookingapp.data.models.MovieBookingModelImpl
import com.jshelf.themoviebookingapp.data.vos.MovieVO
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.activtiy_movie_detail.*


class MovieDetailActivity : AppCompatActivity() {
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    var mMovieId: Int? = 0
    var mMovieTitle: String? = null
    var mMovieRunTime: Int? = 0
    var mPosterPath: String? = ""

    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }

    lateinit var btnAdapter: ButtonAdapter
    lateinit var castAdapter: CastAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activtiy_movie_detail)
        setUpRecyclerView()
        setUpBackListener()
        setUpNextView()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        movieId?.let {
            requestData(it)
            mMovieId = it
        }

    }


    private fun setUpBackListener() {
        backBtn.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpRecyclerView() {
        btnAdapter = ButtonAdapter()
        rcBtnView.adapter = btnAdapter
        rcBtnView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        castAdapter = CastAdapter()
        rcImgCircle.adapter = castAdapter
        rcImgCircle.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun setUpNextView() {
        btnGetTicket.setOnClickListener {
            Log.d(TAG, "MovieID" + mMovieId)
            mMovieId?.let {
                startActivity(
                    mMovieRunTime?.let { it1 ->
                        TimeActivity.newIntent(
                            this,
                            movieId = it,
                            movieTitle = mMovieTitle.toString(),
                            movieRunTime = it1,
                            posterPath = mPosterPath.toString()
                        )
                    }
                )
            }

        }
    }

    private fun requestData(movieId: Int) {
        mMovieBookingModel.getMovieDetail(
            movieId = movieId.toString(),
            onSuccess = {
                bindData(it)
                mMovieTitle = it.originalTitle
                it?.genre?.let { it1 -> btnAdapter.setData(it1) }

                mMovieRunTime = it.runTime
            },
            onFailure = {

            }
        )

        mMovieBookingModel.getCreditByMovie(movieId = movieId.toString(),
            onSuccess = {
                castAdapter.setData(it.first)
            },
            onFailure = {

            }
        )
    }

    private fun bindData(movie: MovieVO) {
        mPosterPath = movie.posterPath
        Glide.with(this).load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(ivDetail)
        tvTitle.text = movie.title ?: ""
        tvRunTime.text = movie.runTime?.toString() + " mins"
        tvRatingImdb.text = movie.voteAverage?.toString() ?: ""

        tvRtBar.rating = movie.getRatingBasedOnFiveStar()
        tvContent.text = movie.overview

        //  bindGenre(movie, movie.genre ?: listOf())

    }


}