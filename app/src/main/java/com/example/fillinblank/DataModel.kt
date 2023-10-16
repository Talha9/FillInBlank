package com.example.fillinblank

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(val txt: String?, val case:String?=null):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    companion object : Parceler<DataModel> {

        override fun DataModel.write(parcel: Parcel, flags: Int) {
            parcel.writeString(txt)
            parcel.writeString(case)
        }

        override fun create(parcel: Parcel): DataModel {
            return DataModel(parcel)
        }
    }
}
