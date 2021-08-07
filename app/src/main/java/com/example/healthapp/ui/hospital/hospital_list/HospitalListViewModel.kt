package com.example.healthapp.ui.hospital.hospital_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.healthapp.data.ApiRepository
import com.example.healthapp.data.entity.hospital.Hospitals
import com.example.healthapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HospitalListViewModel @Inject constructor(

    val savedStateHandle: SavedStateHandle,
    val apiRepository: ApiRepository
): ViewModel() {
    fun fetchHospitalList() : LiveData<Resource<Hospitals>> =
        apiRepository.getHospitalList()

}
