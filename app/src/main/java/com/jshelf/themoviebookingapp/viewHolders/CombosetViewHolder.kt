package com.jshelf.themoviebookingapp.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jshelf.themoviebookingapp.data.vos.SnackVO
import com.jshelf.themoviebookingapp.delegates.ComboSetDelegate
import kotlinx.android.synthetic.main.comboset_viewholder.view.*

class CombosetViewHolder(itemView: View, private val mDelegate: ComboSetDelegate) :
    RecyclerView.ViewHolder(itemView) {
    var checkOperatoin: Int = 0
    var snackPrice: Int = 0
    var snackId: Int = 0
    fun bindData(snackVO: SnackVO) {
        itemView.tvComboTitle.text = snackVO.name
        itemView.tvDescription.text = snackVO.description
        itemView.tvSnackCost.text = snackVO.price.toString() + "$"
        itemView.btnSnackCount.text = snackVO.quantity.toString()
        snackId = snackVO.id

        snackPrice = snackVO.price

        itemView.btnMinus.setOnClickListener {
            checkOperatoin = 0
            mDelegate.onTapPlusMinus(checkOperatoin, snackPrice, snackId)
        }
        itemView.btnPlus.setOnClickListener {
            checkOperatoin = 1
            mDelegate.onTapPlusMinus(checkOperatoin, snackPrice, snackId)
        }
    }
}