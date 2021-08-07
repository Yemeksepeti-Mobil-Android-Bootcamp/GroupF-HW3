package com.example.healthapp.data.remote

import com.example.healthapp.data.entity.hospital.HospitalResponse
import com.example.healthapp.data.entity.hospital.Hospitals
import com.example.healthapp.data.entity.hospital.HospitalRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkApiService {

    @GET("hospitals")
    suspend fun getHospitals(): Response<Hospitals>

    //Post istekleri içlerine body alırlar
    @POST("hospitals")
    suspend fun postHospital(@Body request: HospitalRequest): Response<HospitalResponse>
}