package com.example.healthapp.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.healthapp.data.entity.user.User
import com.example.healthapp.databinding.FragmentUserBinding
import com.example.healthapp.utils.ViewPagerAdapter
import com.example.healthapp.view_pager.AppointmentListFragment
import com.example.healthapp.view_pager.UserInfoFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val mFireStore = FirebaseFirestore.getInstance()

    private lateinit var auth: FirebaseAuth

    private lateinit var dotsIndicator: DotsIndicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        auth = Firebase.auth
        binding.apply {
            btnAppointment.setOnClickListener {
                val action = UserFragmentDirections.actionUserFragmentToHospitalListFragment()
                findNavController().navigate(action)
            }
            imageButton.setOnClickListener {
                auth.signOut()
                val action = UserFragmentDirections.actionUserFragmentToLoginFragment()
                findNavController().navigate(action)
            }
            dotsIndicator = springDotsIndicator
        }
        initViewPager()
    }

    private fun initViewPager() {
        //Fragment List
        val fragmentList = arrayListOf(
            UserInfoFragment(),
            AppointmentListFragment()
        )
        val adapter =
            ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        binding.apply {
            viewPager.adapter = adapter
            dotsIndicator.setViewPager2(viewPager)
        }
    }

    private fun initViews() {
        mFireStore.collection("Users")
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)
                binding.textViewName.text = user?.firstName
            }
    }

    private fun getCurrentUserID(): String {
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