package com.jshelf.themoviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jshelf.themoviebookingapp.data.vos.PaymentMethodVO

@Dao
interface PaymentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPayment(paymentMethodList: List<PaymentMethodVO>)

    @Query("SELECT * FROM paymentmethod")
    fun getPaymentMethodList(): List<PaymentMethodVO>
}