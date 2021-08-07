package com.example.healthapp.ui.listeners

import com.example.healthapp.data.entity.hospital.HospitalsItem

interface IHospitalClickListener {
    fun onClick(name: HospitalsItem)
}