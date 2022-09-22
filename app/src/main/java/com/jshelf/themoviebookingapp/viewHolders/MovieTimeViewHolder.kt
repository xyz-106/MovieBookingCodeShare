package com.jshelf.themoviebookingapp.viewHolders

import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.jshelf.themoviebookingapp.data.vos.DateTimeVO
import com.jshelf.themoviebookingapp.delegates.DateDelegate
import kotlinx.android.synthetic.main.activity_time_viewholder.view.*
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class MovieTimeViewHolder(itemView: View, private val mDelegate: DateDelegate) :
    RecyclerView.ViewHolder(itemView) {
    var day: String? = ""
    var date: String? = ""
    var mDateTimeVO: DateTimeVO? = null
    private var mDateList: List<DateTimeVO>? = listOf()

    init {

        itemView.txtDay.setOnClickListener {
            day?.let { it1 -> mDelegate.onTapDate(it1) }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun bindData(dateTimeVO: DateTimeVO) {
        //mDateTimeVO = dateTimeVO
        mDateTimeVO = dateTimeVO
        itemView.txtDay.text = dateTimeVO.day
        itemView.txtDate.text = dateTimeVO.date
        day = itemView.txtDate.text as String

       colorSetPaleWhite()

        if (dateTimeVO.isSelected == true) {
            checkToday()
            colorSetWhite()

        } else if (dateTimeVO.isSelected == false) {
            checkToday()
            colorSetPaleWhite()


        } else if (mDateTimeVO?.date == LocalDate.now().toString().takeLast(2)) {
            colorSetWhite()
        }
    }

    private fun colorSetPaleWhite() {
        itemView.txtDay.setTextColor(Color.parseColor("#D0D8EB"))
        itemView.txtDate.setTextColor(Color.parseColor("#D0D8EB"))
    }

    private fun colorSetWhite() {
        itemView.txtDay.setTextColor(Color.parseColor("#FFFFFF"))
        itemView.txtDate.setTextColor(Color.parseColor("#FFFFFF"))
    }

    private fun checkToday() {
        if (mDateTimeVO?.date == LocalDate.now().toString().takeLast(2)) {
            colorSetWhite()
        }
    }

}




