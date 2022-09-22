package com.jshelf.themoviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jshelf.themoviebookingapp.data.vos.MovieCinemaVO
import com.jshelf.themoviebookingapp.delegates.TimeDelegate
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.viewHolders.ExternalViewHolder

class ExternalAdapter(private val mDelegate: TimeDelegate) :
    RecyclerView.Adapter<ExternalViewHolder>() {
    private var mMovieCinemaList: List<MovieCinemaVO> = arrayListOf()
    var onTapDateOrNotInExternalAdapter: Boolean = false
    var changeColorExternal:Boolean=false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExternalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.external_rc_viewholder, parent, false)

        return ExternalViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: ExternalViewHolder, position: Int) {
        if (mMovieCinemaList.isNotEmpty()) {
            changeColorExternal = position==0 && onTapDateOrNotInExternalAdapter==false
            holder.bindData(mMovieCinemaList[position],onTapDateOrNotInExternalAdapter,changeColorExternal)
        }
    }

    override fun getItemCount(): Int {
        return mMovieCinemaList.count()
    }

    fun setData(movie: List<MovieCinemaVO>, onTapDateOrNot: Boolean) {
        mMovieCinemaList = movie
        onTapDateOrNotInExternalAdapter = onTapDateOrNot
        mMovieCinemaList?.forEach {
        }
        notifyDataSetChanged()
    }
}