package com.example.healthapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.data.entity.hospital.Hospitals
import com.example.healthapp.data.entity.hospital.HospitalsItem
import com.example.healthapp.databinding.ItemHospitalBinding
import com.example.healthapp.ui.listeners.IHospitalClickListener

class HospitalListAdapter: RecyclerView.Adapter<HospitalListAdapter.HospitalViewHolder>()  {

    var hospitalList: Hospitals?= null

    private var listener: IHospitalClickListener? = null

    class HospitalViewHolder(val binding: ItemHospitalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(HospitalsItem: HospitalsItem, listener: IHospitalClickListener?) {
            binding.hospitalName.text = HospitalsItem.name
            binding.hospitalAddress.text = HospitalsItem.address
            binding.itemFoodCardView.setOnClickListener {listener?.onClick(HospitalsItem)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        return HospitalViewHolder( ItemHospitalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
            hospitalList?.get(position)?.let {
                holder.bind(it,listener)
            }
    }

    override fun getItemCount(): Int {
        return hospitalList!!.size
    }

    fun setData(newList: Hospitals?){
        hospitalList = newList
        notifyDataSetChanged()
    }

    fun setHospitalOnClickListener(listener: IHospitalClickListener) {
        this.listener = listener
    }

}