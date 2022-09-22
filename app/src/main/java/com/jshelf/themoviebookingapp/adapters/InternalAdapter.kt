package com.jshelf.themoviebookingapp.adapters

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jshelf.themoviebookingapp.data.vos.TimeSlotVO
import com.jshelf.themoviebookingapp.delegates.TimeDelegate
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.viewHolders.InternalViewHolder

class InternalAdapter(private val mDelegate: TimeDelegate) :
    RecyclerView.Adapter<InternalViewHolder>() {
    private var mMovieTimeList: List<TimeSlotVO> = arrayListOf()
    var onTapDateOrNotInternalAdapter: Boolean = false
    var changeColor: Boolean = false
    var changeColorInternalAdapter: Boolean = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InternalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.internal_rc_viewholder, parent, false)
        return InternalViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: InternalViewHolder, position: Int) {

        if (mMovieTimeList.isNotEmpty()) {
            changeColor = position == 0 && onTapDateOrNotInternalAdapter == false && changeColorInternalAdapter == true
            holder.bindData(mMovieTimeList[position], changeColor, onTapDateOrNotInternalAdapter)
        }
    }

    override fun getItemCount(): Int {
        return mMovieTimeList.count()
    }

    fun setData(movie: List<TimeSlotVO>, onTapDateOrNot: Boolean, changeColorInternal: Boolean) {
        onTapDateOrNotInternalAdapter = onTapDateOrNot
        mMovieTimeList = movie
        changeColorInternalAdapter = changeColorInternal
        notifyDataSetChanged()
    }
}