package com.example.taximuslim.presentation.view.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taximuslim.databinding.DriverOrderItemBinding
import com.example.taximuslim.domain.models.driver.DriverOrderModel
import com.example.taximuslim.domain.models.driver.OrderToDriverModel

class DriverOrderAdapter :
    ListAdapter<OrderToDriverModel, DriverOrderAdapter.ViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<OrderToDriverModel>() {
        override fun areItemsTheSame(
            oldItem: OrderToDriverModel,
            newItem: OrderToDriverModel
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: OrderToDriverModel,
            newItem: OrderToDriverModel
        ) = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = getItem(position)
        holder.bind(order, takeOrderListener)
    }

    class ViewHolder(private val binding: DriverOrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = DriverOrderItemBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(order: OrderToDriverModel) {
            binding.order = order
        }
    }


}