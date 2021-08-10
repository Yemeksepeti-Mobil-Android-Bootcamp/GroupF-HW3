package com.example.healthapp.ui.hospital.hospital_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthapp.R
import com.example.healthapp.data.entity.hospital.HospitalsItem
import com.example.healthapp.databinding.FragmentHospitalListBinding
import com.example.healthapp.ui.adapter.HospitalListAdapter
import com.example.healthapp.ui.listeners.IHospitalClickListener
import com.example.healthapp.utils.Resource
import com.example.healthapp.utils.gone
import com.example.healthapp.utils.show
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HospitalListFragment : Fragment() {

    private lateinit var _binding: FragmentHospitalListBinding
    private val viewModel: HospitalListViewModel by viewModels()

    private val hospitalListAdapter = HospitalListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHospitalListBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.userProfile -> {
                    val action =
                        HospitalListFragmentDirections.actionHospitalListFragmentToUserFragment()
                    findNavController().navigate(action)
                    true
                }
                else -> false
            }
        }

        if (getCurrentUserID() == "LhCisoNqOdVJxBwAwVLTYxhyKxp2") {
            _binding.addHospitalButton.setOnClickListener {
                val action =
                    HospitalListFragmentDirections.actionHospitalListFragmentToHospitalAddFragment()
                findNavController().navigate(action)
            }
        } else {
            _binding.addHospitalButton.isVisible = false
        }

        viewModel.fetchHospitalList().observe(viewLifecycleOwner, {
            //it.status bizim için bir state
            when (it.status) {
                //Bu 3 farklı state de artık ui'ı yönetebilir hale geldik
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()
                    Log.v("HospitalList", "${it.data}")
                    hospitalListAdapter.setData(it.data)
                    initViews()
                }
                Resource.Status.ERROR -> {
                    _binding.progressBar.gone()
                }
            }
        })
    }

    private fun initViews() {
        _binding.hospitalsRecyclerView.adapter = hospitalListAdapter
        _binding.hospitalsRecyclerView.layoutManager = LinearLayoutManager(context)

        hospitalListAdapter.setHospitalOnClickListener(object : IHospitalClickListener {
            override fun onClick(name: HospitalsItem) {

                Log.v("Error", "Error olmuyoooor")
                val action =
                    HospitalListFragmentDirections.actionHospitalListFragmentToHospitalDetailFragment(
                        name.name,
                        name.address
                    )
                findNavController().navigate(action)
            }
        })
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