package com.example.taximuslim.presentation.view.clientorder.list.prediction

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class PredictionAdapter :
    ListAdapter<PredictionModel, PredictionViewHolder>(PredictionDiffUtilsCallback()) {

    private var onItemClickListener : ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PredictionViewHolder =
        PredictionViewHolder.from(parent)

    override fun onBindViewHolder(holder: PredictionViewHolder, position: Int) {
        holder.bindView(getItem(position))
        holder.setOnItemClickListener { pos ->
            onItemClickListener?.invoke(getItem(pos).primaryText + " " + getItem(pos).secondaryText)
        }
    }

    fun setOnItemClickListener(listener : ((String) -> Unit)){
        onItemClickListener = listener
    }
}