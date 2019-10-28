package com.example.taximuslim.presentation.view.main.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.place_recycle_list.view.*

class MapsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)  {

    val imageViewBackImage: ImageView = itemView.back_img
    val textViewTextHead: TextView = itemView.text_head

}
