package com.example.taximuslim.presentation.view.clientorder.list.prediction

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.item_prediction.view.*
import java.text.DecimalFormat


class PredictionViewHolder(itemView : View) : BaseViewHolder(itemView) {

    companion object{
        fun from(parent : ViewGroup)=
            PredictionViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.item_prediction,parent, false))

    }

    private val primaryTextView = itemView.primaryTextView
    private val secondaryTextView = itemView.secondaryTextView
    private val distanceTextView = itemView.distance
    private var onItemClickListener : ((Int) -> Unit)? = null

    @SuppressLint("SetTextI18n")
    override fun <T> bindView(item: T) {
        (item as PredictionModel).apply {
            primaryTextView.text = primaryText
            secondaryTextView.text = secondaryText
            distanceTextView.text = DecimalFormat("#0.0").format(distance) + " км"
        }
        itemView.rootView.setOnClickListener{
            onItemClickListener?.invoke(adapterPosition)
        }
    }

    fun setOnItemClickListener(listener : ((Int) -> Unit)){
        onItemClickListener = listener
    }


}