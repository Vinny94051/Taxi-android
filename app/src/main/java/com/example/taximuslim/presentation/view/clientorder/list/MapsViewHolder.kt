package com.example.taximuslim.presentation.view.clientorder.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.viewholder.BaseViewHolder
import com.example.taximuslim.domain.models.guide.GuideCategoryModel
import com.example.taximuslim.utils.toDp
import com.example.taximuslim.utils.toPx
import kotlinx.android.synthetic.main.place_recycle_list.view.*

class MapsViewHolder private constructor(itemView: View) : BaseViewHolder(itemView) {

    companion object {
        fun from(parent: ViewGroup) = MapsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.place_recycle_list,
                parent, false
            )
        )
    }

    private val imageViewBackImage: ImageView = itemView.back_img
    private val textViewTextHead: TextView = itemView.text_head
    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun <T> bindView(item: T) {
        (item as GuideCategoryModel).apply {
            textViewTextHead.text = categoryName

            Glide.with(imageViewBackImage.context)
                .load(imageUrl)
                .centerCrop()
                .override((categoryName.length * 10).toDp() + 40.toDp(), 40.toDp())
                .into(imageViewBackImage)

            itemView.rootView.setOnClickListener {
                onItemClickListener?.invoke(adapterPosition)
            }
        }
    }


    fun setOnItemClickListener(listener: ((Int) -> Unit)) {
        onItemClickListener = listener
    }


}
