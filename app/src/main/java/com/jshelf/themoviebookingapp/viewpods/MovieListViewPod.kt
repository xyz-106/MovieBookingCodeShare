package com.jshelf.themoviebookingapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.jshelf.themoviebookingapp.adapters.MovieListAdapter
import com.jshelf.themoviebookingapp.data.vos.MovieVO
import com.jshelf.themoviebookingapp.delegates.MovieListViewHolderDelegate
import kotlinx.android.synthetic.main.activity_viewpod_movielist.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    lateinit var mMovielistAdapter: MovieListAdapter
    lateinit var mDelegate: MovieListViewHolderDelegate
    override fun onFinishInflate() {
        // setUpMovieListRecyclerView()

        super.onFinishInflate()
    }

    fun setUpMovieListViewPod(titleText: String, delegate: MovieListViewHolderDelegate) {
        nowShowing.text = titleText
        setDelegate(delegate)
        setUpMovieListRecyclerView() //Pass Global Delegate to Adapter to ViewHolder

    }

    private fun setUpMovieListRecyclerView() {
        mMovielistAdapter = MovieListAdapter(mDelegate)
        mvList1.adapter = mMovielistAdapter
        mvList1.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setDelegate(delegate: MovieListViewHolderDelegate) {//Frist, Add Delegate,Adapter Setup Passdown
        this.mDelegate = delegate
    }

    fun setData(movieList: List<MovieVO>) {
        mMovielistAdapter.setNewData(movieList)
    }

    fun setUpComingData(movieList: List<MovieVO>) {
        mMovielistAdapter.setNewData(movieList)
    }

//    fun setUpMovieListViewPod(
//        titleText: String
//    ) {
//        //Call from ViewObject
//        nowShowing.text = titleText
//    }
}