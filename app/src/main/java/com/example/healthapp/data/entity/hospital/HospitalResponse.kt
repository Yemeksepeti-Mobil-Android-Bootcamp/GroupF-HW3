package com.example.healthapp.data.entity.hospital


import com.google.gson.annotations.SerializedName

data class HospitalResponse(
    @SerializedName("address")
    val address: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)