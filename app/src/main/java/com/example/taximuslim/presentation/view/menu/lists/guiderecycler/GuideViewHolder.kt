package com.example.taximuslim.presentation.view.menu.lists.guiderecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.guide_recycle_list.view.*

class GuideViewHolder private constructor(itemView: View) : BaseViewHolder(itemView) {
    override fun <T> bindView(item: T) {

    }

    companion object {
        fun from(parent: ViewGroup) = GuideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.guide_recycle_list,
                parent, false
            )
        )
    }

    val img: ImageView = itemView.background_img
    val textHead: TextView = itemView.element_head
}