package com.example.taximuslim.presentation.view.adapter.guide

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.taximuslim.domain.models.guide.GuideCategoryModel

class MainScreenGuideAdapter : ListAdapter<GuideCategoryModel, MainScreenGuideViewHolder>(
    Companion
) {

    companion object : DiffUtil.ItemCallback<GuideCategoryModel>() {
        override fun areItemsTheSame(
            oldItem: GuideCategoryModel,
            newItem: GuideCategoryModel
        ): Boolean =
            oldItem.categoryId == newItem.categoryId


        override fun areContentsTheSame(
            oldItem: GuideCategoryModel,
            newItem: GuideCategoryModel
        ): Boolean =
            oldItem == newItem
    }


    private var onItemClickListener: ((GuideCategoryModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenGuideViewHolder =
        MainScreenGuideViewHolder.from(
            parent
        )


    override fun onBindViewHolder(viewHolder: MainScreenGuideViewHolder, position: Int) {
        viewHolder.bindView(getItem(position))
        viewHolder.setOnItemClickListener { pos ->
            onItemClickListener?.invoke(getItem(pos))
        }
    }

    fun setOnItemClickListener(listener: ((GuideCategoryModel) -> Unit)) {
        onItemClickListener = listener
    }


}
