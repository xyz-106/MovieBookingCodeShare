package com.jshelf.themoviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jshelf.themoviebookingapp.data.vos.PaymentMethodVO
import com.jshelf.themoviebookingapp.delegates.PaymentMethodDelegate
import com.jshelf.themoviebookingapp.R
import com.jshelf.themoviebookingapp.viewHolders.PaymentViewHolder

class PaymentAdapter(private val mDelegate: PaymentMethodDelegate) :
    RecyclerView.Adapter<PaymentViewHolder>() {
    private var mPaymentList: List<PaymentMethodVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.payment_viewholder, parent, false
        )
        return PaymentViewHolder(view, mDelegate)

    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        if (mPaymentList.isNotEmpty()) {
            holder.bindData(mPaymentList[position])
        }
    }

    override fun getItemCount(): Int {
        return mPaymentList.count()
    }

    fun setData(paymentMethod: List<PaymentMethodVO>) {
        mPaymentList = paymentMethod
        notifyDataSetChanged()
    }
}