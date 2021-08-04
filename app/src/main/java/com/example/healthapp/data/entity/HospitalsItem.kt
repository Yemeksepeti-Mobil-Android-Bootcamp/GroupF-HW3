package com.example.healthapp.data.entity


import com.google.gson.annotations.SerializedName

data class HospitalsItem(
    @SerializedName("address")
    val address: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)