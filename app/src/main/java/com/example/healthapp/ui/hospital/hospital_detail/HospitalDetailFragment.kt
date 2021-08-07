package com.example.healthapp.ui.hospital.hospital_detail

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.navigation.fragment.navArgs
import com.example.healthapp.R
import com.example.healthapp.databinding.FragmentHospitalDetailBinding
import java.util.*

class HospitalDetailFragment : Fragment(R.layout.fragment_hospital_detail), DatePickerDialog.OnDateSetListener{

    private var _binding: FragmentHospitalDetailBinding? = null

    private val binding get() = _binding!!

    private val args: HospitalDetailFragmentArgs by navArgs()

    lateinit var sDay : String
    lateinit var sMonth : String
    lateinit var sYear : String


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

            buttonVaccine.setOnClickListener{

                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                DatePickerDialog(requireActivity(), this@HospitalDetailFragment, year, month, day).show()
            }

            buttonBloodSample.setOnClickListener{

                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                DatePickerDialog(requireActivity(), this@HospitalDetailFragment, year, month, day).show()

            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        sDay = dayOfMonth.toString()
        sMonth = (month+1).toString()
        sYear = year.toString()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}