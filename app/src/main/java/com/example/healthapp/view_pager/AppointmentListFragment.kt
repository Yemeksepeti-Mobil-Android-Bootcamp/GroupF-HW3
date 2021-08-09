package com.example.healthapp.view_pager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R
import com.example.healthapp.data.entity.appointment.AppointmentItem
import com.example.healthapp.ui.adapter.AppointmentListAdapter
import com.example.healthapp.databinding.FragmentUserAppointmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AppointmentListFragment: Fragment(R.layout.fragment_user_appointment) {

    private val mFireStore= FirebaseFirestore.getInstance()

    private var _binding: FragmentUserAppointmentBinding? = null
    private val binding get() = _binding!!

    private val AppointmentListAdapter = AppointmentListAdapter()
    lateinit var appointmentsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserAppointmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
    }

    private fun initViews() {
        binding.appointmentRecyclerView.adapter = AppointmentListAdapter
        binding.appointmentRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun initData() {
        mFireStore.collection("Appointments")
            .get()
            .addOnSuccessListener { result ->
                val list = ArrayList<AppointmentItem>()
                for (document in result) {
                    val appointment = document.toObject(AppointmentItem::class.java)!!
                    if(getCurrentUserId() == appointment.userId){
                        list.add(
                            AppointmentItem(
                                appointment.hospitalName,
                                appointment.hospitalPlace,
                                appointment.userId,
                                appointment.date,
                                appointment.choice,
                            )
                        )
                    }
                }
                AppointmentListAdapter.setData(list)
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
            }
    }

    fun getCurrentUserId(): String {
        // An Instance of currentUser using FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser

        // A variable to assign the currentUserId if it is not null or else it will be blank.
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }
}