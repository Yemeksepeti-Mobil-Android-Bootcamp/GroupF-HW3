package com.example.healthapp.data.entity.hospital


import com.google.gson.annotations.SerializedName

data class HospitalRequest(
    @SerializedName("address")
    val address: String,
    @SerializedName("name")
    val name: String
)