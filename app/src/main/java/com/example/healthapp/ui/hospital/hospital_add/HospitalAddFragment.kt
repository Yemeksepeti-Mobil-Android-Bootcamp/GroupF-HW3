package com.example.healthapp.ui.hospital.hospital_add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.healthapp.databinding.FragmentHospitalAddBinding
import com.example.healthapp.utils.Resource
import com.example.healthapp.utils.gone
import com.example.healthapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HospitalAddFragment : Fragment() {

    private lateinit var _binding: FragmentHospitalAddBinding

    private val viewModel: HospitalAddViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHospitalAddBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.btnAddHospital.setOnClickListener {
            val name = _binding.edtTxtHospitalName.text.toString()
            val address = _binding.edtTxtHospitalAddress.text.toString()

            viewModel.addHospital(name, address)
                .observe(viewLifecycleOwner, Observer {
                    when(it.status){
                        //Bu 3 farklı state de artık ui'ı yönetebilir hale geldik
                        Resource.Status.LOADING -> {
                            _binding.progressBar.show()
                        }
                        Resource.Status.SUCCESS -> {
                            _binding.progressBar.gone()
                            val action =
                                HospitalAddFragmentDirections.actionHospitalAddFragmentToHospitalListFragment()
                            findNavController().navigate(action)

                        }
                        Resource.Status.ERROR -> {
                            _binding.progressBar.gone()
                        }
                    }
            })
        }

        _binding.textCancelHospital.setOnClickListener {
            val action =
                HospitalAddFragmentDirections.actionHospitalAddFragmentToHospitalListFragment()
            findNavController().navigate(action)
        }
    }

}