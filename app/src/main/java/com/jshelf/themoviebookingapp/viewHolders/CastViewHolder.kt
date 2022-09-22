package com.jshelf.themoviebookingapp.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jshelf.themoviebookingapp.data.vos.ActorVO
import com.jshelf.themoviebookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.activity_cast_view_holder.view.*

class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(actorVO: ActorVO) {
        Glide.with(itemView.context).load("$IMAGE_BASE_URL${actorVO.profilePath}")
            .into(itemView.ivCastImg)
    }
}