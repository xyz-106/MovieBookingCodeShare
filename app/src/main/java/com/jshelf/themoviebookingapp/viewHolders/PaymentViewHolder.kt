package com.jshelf.themoviebookingapp.viewHolders

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jshelf.themoviebookingapp.data.vos.PaymentMethodVO
import com.jshelf.themoviebookingapp.delegates.PaymentMethodDelegate
import kotlinx.android.synthetic.main.payment_viewholder.view.*

class PaymentViewHolder(itemView: View, private val methodDelegate: PaymentMethodDelegate) :
    RecyclerView.ViewHolder(itemView) {
    var cardId: Int = 0

    init {

    }

    fun bindData(paymentMethodVO: PaymentMethodVO) {
        cardId = paymentMethodVO.id
        itemView.tvCardMethod.text = paymentMethodVO.name
        itemView.tvTypeofCard.text = paymentMethodVO.description

        itemView.tvCardMethod.setOnClickListener {
            methodDelegate.onTapPaymentMethod(cardId)
        }

        if(paymentMethodVO.isSelected==true){
            itemView.tvCardMethod.setTextColor(Color.parseColor("#623EEA"))
            itemView.tvTypeofCard.setTextColor(Color.parseColor("#623EEA"))
        }
        else{
            itemView.tvCardMethod.setTextColor(Color.parseColor("#000000"))
            itemView.tvTypeofCard.setTextColor(Color.parseColor("#D9D9EF"))
        }
    }
}