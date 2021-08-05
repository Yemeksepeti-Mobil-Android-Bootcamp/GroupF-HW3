package com.example.healthapp.ui.hospital.hospital_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.healthapp.R
import com.example.healthapp.databinding.FragmentHospitalDetailBinding

class HospitalDetailFragment : Fragment(R.layout.fragment_hospital_detail) {

    private var _binding: FragmentHospitalDetailBinding? = null

    private val binding get() = _binding!!

    private val args: HospitalDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHospitalDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            textViewHospitalName.text = args.name
            textViewHospitalAddress.text = args.address
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}