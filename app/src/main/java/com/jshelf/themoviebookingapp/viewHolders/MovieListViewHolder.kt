package com.jshelf.themoviebookingapp.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jshelf.themoviebookingapp.data.vos.MovieVO
import com.jshelf.themoviebookingapp.delegates.MovieListViewHolderDelegate
import com.jshelf.themoviebookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_list1.view.*

class MovieListViewHolder(itemView: View, private val mMovieDelegate: MovieListViewHolderDelegate) :
    RecyclerView.ViewHolder(itemView) {

    private var mMovieVO: MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovieVO?.let {
                mMovieDelegate.onTapFromMovie(it.id)
            }
        }
    }

    fun bindData(movie: MovieVO) {
        mMovieVO = movie
        Glide.with(itemView.context).load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.imgMovie)

        itemView.txtMovieName.text = movie.title

    }

}