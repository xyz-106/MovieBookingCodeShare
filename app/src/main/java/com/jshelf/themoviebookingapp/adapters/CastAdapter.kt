package com.jshelf.themoviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jshelf.themoviebookingapp.data.vos.ActorVO
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.viewHolders.CastViewHolder


class CastAdapter : RecyclerView.Adapter<CastViewHolder>() {
    private var mActorList: List<ActorVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_cast_view_holder, parent, false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        if (mActorList.isNotEmpty()) {
            holder.bindData(mActorList[position])
        }
    }

    override fun getItemCount(): Int {
        return mActorList.count()
    }

    fun setData(cast: List<ActorVO>) {
        mActorList = cast
        notifyDataSetChanged()
    }
}