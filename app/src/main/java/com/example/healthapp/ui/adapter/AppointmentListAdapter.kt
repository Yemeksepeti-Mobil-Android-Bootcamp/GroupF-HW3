package com.example.healthapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.data.entity.appointment.AppointmentItem
import com.example.healthapp.data.entity.appointment.Appointments
import com.example.healthapp.databinding.ItemAppointmentBinding

class AppointmentListAdapter: RecyclerView.Adapter<AppointmentListAdapter.AppointmentViewHolder>()  {

    private var listAppointments = ArrayList<AppointmentItem>()

    class AppointmentViewHolder(val binding: ItemAppointmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(AppointmentItem: AppointmentItem) {
            binding.hospitalName.text = AppointmentItem.hospitalName
            binding.choice.text = AppointmentItem.choice
            binding.date.text = AppointmentItem.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        return AppointmentListAdapter.AppointmentViewHolder(ItemAppointmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        listAppointments?.get(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return listAppointments!!.size
    }

    fun setData(list: ArrayList<AppointmentItem>) {
        this.listAppointments = list
        notifyDataSetChanged()
    }

}