package com.example.taximuslim.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taximuslim.databinding.DriverOrderItemBinding
import com.example.taximuslim.domain.models.driver.DriverOrderModel

class DriverOrderAdapter: ListAdapter<DriverOrderModel,DriverOrderAdapter.ViewHolder>(Companion){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = getItem(position)
        holder.bind(order)
    }

    class ViewHolder(private val binding: DriverOrderItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(order: DriverOrderModel){
            binding.order = order
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding
                        = DriverOrderItemBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    companion object: DiffUtil.ItemCallback<DriverOrderModel>(){
        override fun areItemsTheSame(
            oldItem: DriverOrderModel,
            newItem: DriverOrderModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: DriverOrderModel,
            newItem: DriverOrderModel
        ): Boolean {
           return newItem == oldItem
        }
    }
}