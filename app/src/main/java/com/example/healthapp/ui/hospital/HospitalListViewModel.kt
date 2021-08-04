package com.example.healthapp.ui.hospital

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.healthapp.data.ApiRepository
import com.example.healthapp.data.entity.Hospitals
import com.example.healthapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HospitalListViewModel @Inject constructor(

    private val savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository
): ViewModel() {
    fun fetchHospitalList() : LiveData<Resource<Hospitals>> =
        apiRepository.getHospitalList()
}
