package com.example.healthapp.data.model

import android.os.Parcelable



data class User(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val height:String="",
    val weight:String="",
    val bloodGroup:String="",
)
