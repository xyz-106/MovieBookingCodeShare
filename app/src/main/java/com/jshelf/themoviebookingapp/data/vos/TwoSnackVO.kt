package com.jshelf.themoviebookingapp.data.vos


import com.google.gson.annotations.SerializedName



data class TwoSnackVO(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("quantity")
    val quantity: Int = 0
)






















//putExtra snackList as Serialiabakle

//    ) : Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readInt(),
//        parcel.readInt()
//    ) {
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    override fun writeToParcel(dest: Parcel?, flags: Int) {
//        dest?.writeInt(id)
//        dest?.writeInt(quantity)
//    }
//
//    companion object CREATOR : Parcelable.Creator<TwoSnackVO> {
//        override fun createFromParcel(parcel: Parcel): TwoSnackVO {
//            return TwoSnackVO(parcel)
//        }
//
//        override fun newArray(size: Int): Array<TwoSnackVO?> {
//            return arrayOfNulls(size)
//        }
//    }
//}