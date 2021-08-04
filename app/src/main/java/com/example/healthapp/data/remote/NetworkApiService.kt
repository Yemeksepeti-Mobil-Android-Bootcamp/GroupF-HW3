package com.example.healthapp.data.remote

import com.example.healthapp.data.entity.Hospitals
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApiService {

    @GET("hospitals")
    suspend fun getHospitals(): Response<Hospitals>
}