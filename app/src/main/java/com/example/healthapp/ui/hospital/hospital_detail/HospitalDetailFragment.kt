package com.example.healthapp.ui.hospital.hospital_detail

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.healthapp.R
import com.example.healthapp.data.entity.appointment.AppointmentItem
import com.example.healthapp.databinding.FragmentHospitalDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.util.*

class HospitalDetailFragment : Fragment(R.layout.fragment_hospital_detail), DatePickerDialog.OnDateSetListener{

    private var _binding: FragmentHospitalDetailBinding? = null

    private val binding get() = _binding!!

    private val args: HospitalDetailFragmentArgs by navArgs()
    private  var mFireStore= FirebaseFirestore.getInstance()

    lateinit var sDay : String
    lateinit var sMonth : String
    lateinit var sYear : String
    lateinit var sDate:String
    lateinit var choice:String




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
                choice="Vaccine"
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                DatePickerDialog(requireActivity(), this@HospitalDetailFragment, year, month, day).show()
            }

            buttonBloodSample.setOnClickListener{
                choice="BloodSample"
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
        sDate=sDay+"."+sMonth+"."+sYear
      val appointment= AppointmentItem(args.name!!, args.address!!,getCurrentUserID(),sDate,choice)

        mFireStore.collection("Appointments")
            .document()
            .set(appointment, SetOptions.merge())
            .addOnSuccessListener {
                Toast.makeText(context, "Randevu oluşturuldu", Toast.LENGTH_SHORT).show()
                val action=HospitalDetailFragmentDirections.actionHospitalDetailFragmentToUserFragment()
                findNavController().navigate(action)

            }.addOnFailureListener {
                Toast.makeText(context, "Randevu oluşturulmada hata alındı", Toast.LENGTH_SHORT).show()
            }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getCurrentUserID(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }
}