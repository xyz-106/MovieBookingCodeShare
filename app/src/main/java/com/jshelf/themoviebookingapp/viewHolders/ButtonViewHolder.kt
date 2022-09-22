package com.jshelf.themoviebookingapp.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jshelf.themoviebookingapp.data.vos.GenreVO
import kotlinx.android.synthetic.main.activity_button_viewholder.view.*

class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var mGenre: GenreVO? = null
    fun bindData(genre: GenreVO) {
        mGenre = genre
        itemView.btnGenre.text = genre.name
    }
}