package com.example.taximuslim.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taximuslim.databinding.DriverOrderHistoryItemBinding
import com.example.taximuslim.databinding.DriverOrderItemBinding
import com.example.taximuslim.domain.models.driver.DriverOrderHistoryModel
import com.example.taximuslim.domain.models.driver.DriverOrderModel

class DriverOrderHistoryAdapter :
    ListAdapter<DriverOrderHistoryModel, DriverOrderHistoryAdapter.ViewHolder>(Companion) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = getItem(position)
        holder.bind(order)
    }

    class ViewHolder(private val binding: DriverOrderHistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: DriverOrderHistoryModel) {
            binding.order = order
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = DriverOrderHistoryItemBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    companion object : DiffUtil.ItemCallback<DriverOrderHistoryModel>() {
        override fun areItemsTheSame(
            oldItem: DriverOrderHistoryModel,
            newItem: DriverOrderHistoryModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: DriverOrderHistoryModel,
            newItem: DriverOrderHistoryModel
        ): Boolean {
            return newItem == oldItem
        }
    }
}