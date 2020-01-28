package com.example.taximuslim.presentation.view.clientorder.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.place_recycle_list.view.*

class MapsViewHolder private constructor(itemView : View) : BaseViewHolder(itemView)  {

    companion object{
        fun from(parent : ViewGroup) =  MapsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.place_recycle_list,
                parent, false
            )
        )
    }

    override fun <T> bindView(item: T) {

    }

    val imageViewBackImage: ImageView = itemView.back_img
    val textViewTextHead: TextView = itemView.text_head

}
