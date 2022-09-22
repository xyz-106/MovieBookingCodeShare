package com.jshelf.themoviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jshelf.themoviebookingapp.data.vos.GenreVO
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.viewHolders.ButtonViewHolder

class ButtonAdapter : RecyclerView.Adapter<ButtonViewHolder>() {

    private var mGenre: List<GenreVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_button_viewholder, parent, false)
        return ButtonViewHolder(view)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        if (mGenre.isNotEmpty()) {
            holder.bindData(mGenre[position])
        }
    }

    override fun getItemCount(): Int {
        return mGenre.count()
    }

    fun setData(genre: List<GenreVO>) {
        mGenre = genre
        notifyDataSetChanged()
    }
}