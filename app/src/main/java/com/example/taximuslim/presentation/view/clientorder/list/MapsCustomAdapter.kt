package com.example.taximuslim.presentation.view.clientorder.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taximuslim.App
import com.example.taximuslim.domain.models.guide.GuideCategoryModel

class MapsCustomAdapter(private var list: List<GuideCategoryModel> = arrayListOf()) :
    RecyclerView.Adapter<MapsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapsViewHolder =
        MapsViewHolder.from(parent)


    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MapsViewHolder, position: Int) {
        holder.textViewTextHead.text = list[position].categoryName

        Glide.with(holder.imageViewBackImage.context)
            .load(list[position].imageUrl)
            .into(holder.imageViewBackImage)
    }

    fun replaceAll(data: List<GuideCategoryModel>) {
        list = data
        notifyDataSetChanged()
    }
}