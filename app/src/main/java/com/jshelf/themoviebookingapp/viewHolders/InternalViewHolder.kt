package com.jshelf.themoviebookingapp.viewHolders

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.data.vos.TimeSlotVO
import com.jshelf.themoviebookingapp.delegates.TimeDelegate
import kotlinx.android.synthetic.main.internal_rc_viewholder.view.*

class InternalViewHolder(itemView: View, private val mDelegate: TimeDelegate) :
    RecyclerView.ViewHolder(itemView) {
    var timeId: Int? = 0
    var mSelected: Boolean? = false


    init {

        itemView.btnTime.setOnClickListener {
            timeId?.let { it1 ->

                mDelegate.onTapTime(it1)

            }
        }

    }

    @SuppressLint("ResourceAsColor")
    fun bindData(time: TimeSlotVO, changeColor: Boolean, onTapDateOrNot: Boolean) {

        itemView.btnTime.text = time.startTime
        timeId = time.cinemaDayTimeId
        mSelected = time.isSelected

        setColorPaleWhite()
        if (changeColor && !onTapDateOrNot) {
            setColorPaleViolet()
            Log.d(TAG, "FIGHTING ENTER")
        } else if (mSelected == true && onTapDateOrNot && !changeColor) {
            setColorPaleViolet()

        } else if (mSelected == false && onTapDateOrNot == true && changeColor == false) {
            setColorPaleWhite()
        }
    }

    private fun setColorPaleViolet() {

        itemView.btnTime.setTextColor(Color.rgb(255, 255, 255))
        itemView.btnTime.background =
            ContextCompat.getDrawable(itemView.context, R.drawable.background_violet_type)
    }

    private fun setColorPaleWhite() {

        itemView.btnTime.setTextColor(Color.rgb(0, 0, 0))
        itemView.btnTime.background =
            ContextCompat.getDrawable(itemView.context, R.drawable.background_movie_type)
    }
}