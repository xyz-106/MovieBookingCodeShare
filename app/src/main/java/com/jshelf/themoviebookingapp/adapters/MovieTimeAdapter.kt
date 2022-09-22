package com.jshelf.themoviebookingapp.adapters

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.jshelf.themoviebookingapp.data.vos.DateTimeVO
import com.jshelf.themoviebookingapp.delegates.DateDelegate
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.viewHolders.MovieTimeViewHolder

class MovieTimeAdapter(

    private val mDelegate: DateDelegate
) :
    RecyclerView.Adapter<MovieTimeViewHolder>() {
    private var mDateDayList: List<DateTimeVO> = arrayListOf()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTimeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_time_viewholder, parent, false)
        return MovieTimeViewHolder(view, mDelegate)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MovieTimeViewHolder, position: Int) {
        if (mDateDayList.isNotEmpty()) {
            holder.bindData(mDateDayList[position])
        }
    }

    override fun getItemCount(): Int {
        return mDateDayList.size
    }

    fun setData(dateList: List<DateTimeVO>) {
        mDateDayList = dateList
        notifyDataSetChanged()
    }
}