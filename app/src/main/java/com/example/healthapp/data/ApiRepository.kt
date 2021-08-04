package com.example.healthapp.data

import com.example.healthapp.data.remote.RemoteDataSource
import com.example.healthapp.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
){

    fun getHospitalList() = performNetworkOperation {
        remoteDataSource.fetchHospitals()
    }
}