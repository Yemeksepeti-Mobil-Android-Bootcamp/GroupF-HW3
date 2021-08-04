package com.example.healthapp.ui.hospital

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.data.entity.Hospitals
import com.example.healthapp.data.entity.HospitalsItem
import com.example.healthapp.databinding.FragmentHospitalListBinding
import com.example.healthapp.ui.adapter.HospitalListAdapter
import com.example.healthapp.ui.listeners.IHospitalClickListener
import com.example.healthapp.utils.Resource
import com.example.healthapp.utils.gone
import com.example.healthapp.utils.show
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HospitalListFragment : Fragment() {

    private lateinit var _binding: FragmentHospitalListBinding
    private val viewModel: HospitalListViewModel by viewModels()

    private val HospitalListAdapter = HospitalListAdapter()
    lateinit var hospitalsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHospitalListBinding.inflate(inflater,container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val repository = ApiRepository()
        //val viewModelFactory = MainViewModelFactory(repository)

        viewModel.fetchHospitalList().observe(viewLifecycleOwner, Observer {
            //it.status bizim için bir state
            when(it.status){
                //Bu 3 farklı state de artık ui'ı yönetebilir hale geldik
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()
                    Log.v("HospitalList","${it.data}")
                    HospitalListAdapter.setData(it.data)
                    initViews(view)
                }
                Resource.Status.ERROR -> {
                    _binding.progressBar.gone()
                }
            }
        })
    }

    private fun initViews(view: View) {
        _binding.hospitalsRecyclerView.adapter = HospitalListAdapter
        _binding.hospitalsRecyclerView.layoutManager = LinearLayoutManager(context)

        HospitalListAdapter.setHospitalOnClickListener(object : IHospitalClickListener {
           override fun onClick(hospitalsItem: HospitalsItem) {

               Log.v("Error","Error olmuyoooor")
               val action =
                  HospitalListFragmentDirections.actionHospitalListFragmentToHospitalDetailFragment(
                      hospitalsItem.name,
                      hospitalsItem.address
                  )
              findNavController().navigate(action)
            }
        })
    }
}