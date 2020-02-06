package com.example.taximuslim.presentation.view.mainscreen.menu.fragments.guide

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.taximuslim.domain.models.guide.GuideCategoryModel
import com.example.taximuslim.presentation.view.adapter.guide.MainScreenGuideAdapter

class GuideAdapter :
    ListAdapter<GuideCategoryModel, GuideViewHolder>(MainScreenGuideAdapter.Companion) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder =
        GuideViewHolder.from(
            parent
        )


    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

}