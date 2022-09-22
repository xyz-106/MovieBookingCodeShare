package com.jshelf.themoviebookingapp.viewHolders

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.data.vos.MovieSeatSelectableVO
import com.jshelf.themoviebookingapp.delegates.SeatDelegate
import kotlinx.android.synthetic.main.movie_seat_viewholder.view.*

class MovieSeatViewHolder(itemView: View, private val mDelegate: SeatDelegate) :
    RecyclerView.ViewHolder(itemView) {
    var seatName: String? = ""
    var selectedSeat: Boolean? = false

    init {
    }

    fun bindData(data: MovieSeatSelectableVO) {

        when (data.type) {
            "text" -> {
                itemView.tvMovieSeatTitle.text = data.symbol
                itemView.tvMovieSeatTitle.setTextColor(Color.parseColor("#000000"))
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context, R.color.white
                    )
                )
            }
            "space" -> {
                itemView.tvMovieSeatTitle.text = ""
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context, R.color.white
                    )
                )
            }
            "taken" -> {
                itemView.tvMovieSeatTitle.text = data.seatName
                itemView.background = ContextCompat.getDrawable(
                    itemView.context, R.drawable.background_movie_seat_taken
                )
            }
            "available" -> {
                selectedSeat = data.isSelected
                seatName = data.seatName
                itemView.tvMovieSeatTitle.text = data.seatName
                itemView.tvMovieSeatTitle.setTextColor(Color.parseColor("#000000"))
                itemView.background = ContextCompat.getDrawable(
                    itemView.context, R.drawable.background_movie_seat_available
                )
                itemView.tvMovieSeatTitle.setOnClickListener {
                    selectedSeat = true
                    mDelegate.onTapSeat(seatName.toString())
                }
            }
        }

        if( data.isSelected == true) {
            itemView.tvMovieSeatTitle.text = data.seatName
            itemView.background = ContextCompat.getDrawable(
                itemView.context, R.drawable.backgroun_change_seat_color
            )
            itemView.tvMovieSeatTitle.setTextColor(Color.parseColor("#FFFFFF"))
        }
//        when {
//            data.isMovieSeatAvailable() == true -> {
//                itemView.tvMovieSeatTitle.visibility = View.GONE
//                itemView.background = ContextCompat.getDrawable(
//                    itemView.context, R.drawable.background_movie_seat_available
//                )
//            }
//
//            data.isMovieSeatTaken() == true -> {
//                itemView.tvMovieSeatTitle.visibility = View.GONE
//                itemView.background = ContextCompat.getDrawable(
//                    itemView.context, R.drawable.background_movie_seat_taken
//                )
//            }
//
//            data.isMovieSeatRowTitle() -> {
//                itemView.tvMovieSeatTitle.visibility = View.VISIBLE
//                itemView.tvMovieSeatTitle.text = data.title
//                itemView.setBackgroundColor(
//                    ContextCompat.getColor(
//                        itemView.context, R.color.white
//                    )
//                )
//            }
//            else -> {
//                itemView.tvMovieSeatTitle.visibility = View.GONE
//                itemView.setBackgroundColor(
//                    ContextCompat.getColor(
//                        itemView.context, R.color.white
//                    )
//                )
//            }
//        }
    }
}
