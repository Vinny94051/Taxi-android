package com.example.taximuslim.presentation.view.mainscreen.menu.fragments.guide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.viewholder.BaseViewHolder
import com.example.taximuslim.domain.models.guide.GuideCategoryModel
import kotlinx.android.synthetic.main.guide_recycle_list.view.*

class GuideViewHolder private constructor(itemView: View) : BaseViewHolder(itemView) {


    companion object {
        fun from(parent: ViewGroup) =
            GuideViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.guide_recycle_list,
                    parent, false
                )
            )
    }

    private val img: ImageView = itemView.backgroundImg
    private val textHead: TextView = itemView.elementHead

    override fun <T> bindView(item: T) {
        (item as GuideCategoryModel).apply {
            textHead.text = categoryName

            Glide.with(img.context)
                .load(imageUrl)
                .into(img)
        }
    }
}