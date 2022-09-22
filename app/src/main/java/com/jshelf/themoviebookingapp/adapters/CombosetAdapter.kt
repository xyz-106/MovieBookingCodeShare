package com.jshelf.themoviebookingapp.adapters

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jshelf.themoviebookingapp.data.vos.SnackVO
import com.jshelf.themoviebookingapp.delegates.ComboSetDelegate
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.viewHolders.CombosetViewHolder

class CombosetAdapter(private val mDelegate: ComboSetDelegate) :
    RecyclerView.Adapter<CombosetViewHolder>() {
    private var mSnackList: List<SnackVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CombosetViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.comboset_viewholder, parent, false)
        return CombosetViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: CombosetViewHolder, position: Int) {
        if (mSnackList.isNotEmpty()) {
            holder.bindData(mSnackList[position])
        }
    }

    override fun getItemCount(): Int {
        return mSnackList.count()
    }

    fun setData(snackList: List<SnackVO>) {
        mSnackList = snackList
        notifyDataSetChanged()

    }
}