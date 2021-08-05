package com.example.healthapp.data.remote

import com.example.healthapp.data.entity.HospitalsItem
import com.example.healthapp.data.entity.hospital.HospitalRequest
import com.example.healthapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: NetworkApiService) :
    BaseDataSource(){

    suspend fun fetchHospitals() = getResult { apiService.getHospitals() }

    //Post Fonksiyonu
    suspend fun postHospital(request: HospitalRequest) = getResult { apiService.postHospital(request) }

    }

