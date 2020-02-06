package com.example.taximuslim.presentation.view.adapter.guide

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.taximuslim.domain.models.guide.GuideCategoryModel

class DrawerGuideAdapter :
    ListAdapter<GuideCategoryModel, GuideViewHolder>(MainScreenGuideAdapter.Companion) {

    private var onItemClickListener : ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder =
        GuideViewHolder.from(
            parent
        )


    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {
        holder.bindView(getItem(position))
        holder.setOnItemClickListener { categoryId ->
            onItemClickListener?.invoke(categoryId)
        }
    }

    fun setOnItemClickListener(listener : ((Int) -> Unit)){
        onItemClickListener = listener
    }

}