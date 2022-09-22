package com.jshelf.themoviebookingapp.adapters

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jshelf.themoviebookingapp.data.vos.MovieSeatSelectableVO
import com.jshelf.themoviebookingapp.delegates.SeatDelegate
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.viewHolders.MovieSeatViewHolder

class MovieSeatAdapter(private val mDelegate: SeatDelegate) :
    RecyclerView.Adapter<MovieSeatViewHolder>() {
    private var mMovieSeats: List<MovieSeatSelectableVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSeatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_seat_viewholder, parent, false)
        return MovieSeatViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: MovieSeatViewHolder, position: Int) {
        if (mMovieSeats.isNotEmpty()) {
            holder.bindData(mMovieSeats[position])
        }
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "COunt" + mMovieSeats.count())
        return mMovieSeats.count()
    }

    fun setData(movieSeat: List<MovieSeatSelectableVO>) {
        mMovieSeats = movieSeat
        Log.d(TAG, "Seat Adapter" + mMovieSeats)
        notifyDataSetChanged()
    }

//    @SuppressLint("NotifyDataSetChanged")
//    fun setNewData(moviSeats: List<MovieSeatVO>) {
//        this.mMovieSeats = moviSeats
//        notifyDataSetChanged()
//    }
}