package com.jshelf.themoviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jshelf.themoviebookingapp.data.vos.MovieVO
import com.jshelf.themoviebookingapp.delegates.MovieListViewHolderDelegate
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.viewHolders.MovieListViewHolder

class MovieListAdapter(private val mDelegate: MovieListViewHolderDelegate) :
    RecyclerView.Adapter<MovieListViewHolder>() {
    private var mMovieList: List<MovieVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_list1, parent, false)
        return MovieListViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()) {
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieList.count()
    }

    fun setNewData(movieList: List<MovieVO>) {
        mMovieList = movieList
        notifyDataSetChanged()
    }


}