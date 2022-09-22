package com.jshelf.themoviebookingapp.adapters

import alirezat775.lib.carouselview.CarouselAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.data.vos.CardVO
import com.jshelf.themoviebookingapp.data.vos.UserDataVO
import com.jshelf.themoviebookingapp.delegates.PassListDelegate
import kotlinx.android.synthetic.main.item_carousel.view.*


class CarouselViewAdapter() : CarouselAdapter() {

    private lateinit var mUserDataList: UserDataVO
    var mCardList: List<CardVO>? = listOf()
    private var vh: CarouselViewHolder? = null


    fun setData(mDataList: UserDataVO) {
        mUserDataList = mDataList
        mCardList = mUserDataList.cards


        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mCardList?.count() ?: 0
    }
    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {

        when (holder) {
            is MyViewHolder -> {
                vh = holder
                holder.bindData(mCardList?.get(position))
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_carousel, parent, false)
        return MyViewHolder(v)
    }

    inner class MyViewHolder(itemView: View) : CarouselViewHolder(itemView) {
        fun bindData(get: CardVO?) {
            var title: TextView = itemView.tvCardHolderName
            var title2: TextView = itemView.tvExpireDate
            var title3: TextView = itemView.tvLastFourDigit
            title.text = get?.cardHolder.toString()
            title2.text=get?.expDate
            title3.text=get?.getLastFourNumber()
        }


    }


}

