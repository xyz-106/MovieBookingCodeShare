package com.jshelf.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.jshelf.themoviebookingapp.persistence.typeconverters.CardListTypeConverter

@Entity(tableName = "cards")
@TypeConverters(
    CardListTypeConverter::class
)
data class CardVO(
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int = 0,

    @SerializedName("card_holder")
    @ColumnInfo(name = "card_holder")
    val cardHolder: String?,

    @SerializedName("card_number")
    @ColumnInfo(name = "card_number")
    val cardNumber: String?,

    @SerializedName("expiration_date")
    @ColumnInfo(name = "expiration_date")
    val expDate: String?,

    @SerializedName("card_type")
    @ColumnInfo(name = "card_type")
    val cardType: String?
) {
    fun getLastFourNumber(): String {
        return cardNumber?.takeLast(4).toString()
    }
}