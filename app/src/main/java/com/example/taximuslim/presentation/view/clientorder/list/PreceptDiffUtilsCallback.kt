package com.example.taximuslim.presentation.view.clientorder.list

import androidx.recyclerview.widget.DiffUtil
import com.example.taximuslim.domain.models.guide.GuideCategoryModel

class PreceptDiffUtilsCallback : DiffUtil.ItemCallback<GuideCategoryModel>() {
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