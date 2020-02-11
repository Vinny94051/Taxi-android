package com.example.taximuslim.presentation.view.adapter.trip

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.taximuslim.domain.order.models.DriverModel

class DriversAdapter : ListAdapter<DriverModel, DriversViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<DriverModel>() {
        override fun areItemsTheSame(oldItem: DriverModel, newItem: DriverModel): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DriverModel, newItem: DriverModel): Boolean =
            oldItem == newItem

    }

    private var lastSelectedPosition = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DriversViewHolder.from(parent)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DriversViewHolder, position: Int) {
        holder.driverDescription.text = getItem(position).name + getItem(position).car
        holder.radioButton.isChecked = lastSelectedPosition == position
        holder.setOnRadioButtonStateListener { lastCheckedPosition ->
            lastSelectedPosition = lastCheckedPosition
        }
        holder.setRadioButtonClickListener()
    }

    fun getCurrentDriverId(): Int = if (lastSelectedPosition != -1)
        getItem(lastSelectedPosition).id
    else 0
}