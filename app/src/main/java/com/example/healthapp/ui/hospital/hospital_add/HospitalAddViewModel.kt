package com.example.healthapp.ui.hospital.hospital_add

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.healthapp.repository.ApiRepository
import com.example.healthapp.data.entity.hospital.HospitalRequest
import com.example.healthapp.data.entity.hospital.HospitalResponse
import com.example.healthapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HospitalAddViewModel @Inject constructor(

    savedStateHandle: SavedStateHandle,
    val apiRepository: ApiRepository

    ): ViewModel(){

    fun addHospital(name: String, address: String): LiveData<Resource<HospitalResponse>> {
        //Atıcağımız isteğin request objesi
        var request = HospitalRequest(name, address)
        return apiRepository.postHospital(request)
    }

}