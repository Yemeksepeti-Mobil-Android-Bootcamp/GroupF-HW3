package com.example.healthapp.data.remote

import com.example.healthapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: NetworkApiService) :
    BaseDataSource(){

    suspend fun fetchHospitals() = getResult { apiService.getHospitals() }

    }