package com.jshelf.themoviebookingapp.viewHolders

import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jshelf.themoviebookingapp.adapters.InternalAdapter
import com.jshelf.themoviebookingapp.data.vos.MovieCinemaVO
import com.jshelf.themoviebookingapp.delegates.TimeDelegate
import kotlinx.android.synthetic.main.external_rc_viewholder.view.*

class ExternalViewHolder(itemView: View,private val mDelegate:TimeDelegate) : RecyclerView.ViewHolder(itemView) {
    //Adapter
    //init
    lateinit var internalAdapter: InternalAdapter


    init {
        internalAdapter = InternalAdapter(mDelegate)
        itemView.internalRc.adapter = internalAdapter
        itemView.internalRc.layoutManager = GridLayoutManager(itemView.context, 3)
    }

    fun bindData(movie: MovieCinemaVO,onTapDateOrNot:Boolean,changeColorExternal:Boolean) {
        itemView.txtRc.text = movie.cinema
        movie.timeSlots?.let { internalAdapter.setData(it,onTapDateOrNot,changeColorExternal) }
    }
}