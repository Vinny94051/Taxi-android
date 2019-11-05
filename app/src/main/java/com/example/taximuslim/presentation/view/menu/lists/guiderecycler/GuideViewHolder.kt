package com.example.taximuslim.presentation.view.menu.lists.guiderecycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.taximuslim.baseUI.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.guide_recycle_list.view.*

class GuideViewHolder(itemView : View) : BaseViewHolder(itemView)  {
    val img : ImageView = itemView.background_img
    val textHead : TextView = itemView.element_head
}