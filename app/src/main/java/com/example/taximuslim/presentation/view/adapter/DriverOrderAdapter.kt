package com.example.taximuslim.presentation.view.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taximuslim.databinding.DriverOrderItemBinding
import com.example.taximuslim.domain.models.driver.order.DriverOrderModel

class DriverOrderAdapter: ListAdapter<DriverOrderModel,DriverOrderAdapter.ViewHolder>(Companion){

    private var takeOrderListener: (DriverOrderModel) -> Unit = {}

    fun setTakeOrderListener(listener: (DriverOrderModel) -> Unit){
        takeOrderListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = getItem(position)
        holder.bind(order, takeOrderListener)
    }

    class ViewHolder(private val binding: DriverOrderItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(order: DriverOrderModel, takeOrderListener: (DriverOrderModel) -> Unit){
            binding.order = order
            binding.takeOrder.setOnClickListener{
                takeOrderListener(order)
            }
            binding.commentText.setOnClickListener {
                val textView = it as TextView
                if (textView.lineCount == 1) {
                    textView.isSingleLine = false
                    textView.ellipsize = TextUtils.TruncateAt.MARQUEE
                }else{
                    textView.isSingleLine = true
                    textView.ellipsize = TextUtils.TruncateAt.END
                }
            }
            binding.executePendingBindings()
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