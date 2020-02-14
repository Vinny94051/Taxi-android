package com.example.taximuslim.presentation.view.adapter.guide

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.taximuslim.domain.models.guide.PlaceByLocationModel

class PlaceListAdapter : ListAdapter<PlaceByLocationModel, PlacesViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<PlaceByLocationModel>() {
        override fun areItemsTheSame(
            oldItem: PlaceByLocationModel,
            newItem: PlaceByLocationModel
        ): Boolean =
            oldItem.placeId == newItem.placeId

        override fun areContentsTheSame(
            oldItem: PlaceByLocationModel,
            newItem: PlaceByLocationModel
        ): Boolean =
            oldItem == newItem

    }

    private var onItemClickListListener: ((PlaceByLocationModel) -> Unit)? = null
    private var onOrderButtonClickListListener: ((String) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder =
        PlacesViewHolder.from(parent)


    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        holder.bindView(getItem(position))
        holder.setOnItemClickListener { pos ->
            onItemClickListListener?.invoke(getItem(pos))
        }
        holder.setOnOrderButtonClickListener { address ->
            onOrderButtonClickListListener?.invoke(address)
        }
    }

    fun setOnItemClickListener(listener: ((PlaceByLocationModel) -> Unit)) {
        onItemClickListListener = listener
    }


    fun setOnOrderButtonClickListener(listener: ((String) -> Unit)) {
        onOrderButtonClickListListener = listener
    }

}