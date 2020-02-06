package com.example.taximuslim.presentation.view.adapter.guide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.viewholder.BaseViewHolder
import com.example.taximuslim.domain.models.guide.GuideCategoryModel
import com.example.taximuslim.utils.toDp
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
    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun <T> bindView(item: T) {
        (item as GuideCategoryModel).apply {
            textHead.text = categoryName

            Glide.with(img.context)
                .load(imageUrl)
                .transform(RoundedCorners(16.toDp()))
                .into(img)


            itemView.rootView.setOnClickListener {
                onItemClickListener?.invoke(categoryId)
            }
        }
    }

    fun setOnItemClickListener(listener: ((Int) -> Unit)) {
        onItemClickListener = listener
    }
}