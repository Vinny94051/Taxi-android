package com.example.taximuslim.presentation.view.adapter.trip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taximuslim.R

import kotlinx.android.synthetic.main.item_choose_driver.view.*

class DriversViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun from(parent: ViewGroup) = DriversViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_choose_driver, parent, false
            )
        )
    }


    val radioButton: RadioButton = itemView.choseDriverRadioButton
    val driverDescription: TextView = itemView.driverNameAndCarTextView
    private var radioButtonStateListener : ((Int) -> Unit)? = null


    fun setRadioButtonClickListener() = radioButton.setOnClickListener {
        radioButtonStateListener?.invoke(adapterPosition)
    }

    fun setOnRadioButtonStateListener(listener : ((Int) -> Unit)){
        radioButtonStateListener = listener
    }



}