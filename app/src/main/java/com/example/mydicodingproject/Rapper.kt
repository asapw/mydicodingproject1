package com.example.mydicodingproject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rapper(
    var name: String,
    var description: String,
    var photo: Int,
    val albums: String,
    val awards: String
) : Parcelable
