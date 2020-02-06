package com.example.taximuslim.presentation.view.clientorder.list.prediction

import androidx.recyclerview.widget.DiffUtil

class PredictionDiffUtilsCallback : DiffUtil.ItemCallback<PredictionModel>() {
    override fun areItemsTheSame(oldItem: PredictionModel, newItem: PredictionModel): Boolean =
        oldItem.primaryText == newItem.primaryText


    override fun areContentsTheSame(oldItem: PredictionModel, newItem: PredictionModel): Boolean =
        oldItem == newItem
}