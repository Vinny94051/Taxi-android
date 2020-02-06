package com.example.taximuslim.presentation.view.clientorder.list

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.taximuslim.domain.models.guide.GuideCategoryModel

class PreceptAdapter : ListAdapter<GuideCategoryModel, MapsViewHolder>(PreceptDiffUtilsCallback()) {

    private var onItemClickListener: ((GuideCategoryModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapsViewHolder =
        MapsViewHolder.from(parent)


    override fun onBindViewHolder(holder: MapsViewHolder, position: Int) {
        holder.bindView(getItem(position))
        holder.setOnItemClickListener { pos ->
            onItemClickListener?.invoke(getItem(pos))
        }
    }

    fun setOnItemClickListener(listener: ((GuideCategoryModel) -> Unit)) {
        onItemClickListener = listener
    }

}
