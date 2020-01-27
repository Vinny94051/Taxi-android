package com.example.taximuslim.presentation.view.clientorder.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taximuslim.App

class MapsCustomAdapter(private var list: ArrayList<PlacesModel> = arrayListOf()) :
    RecyclerView.Adapter<MapsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapsViewHolder =
        MapsViewHolder.from(parent)


    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MapsViewHolder, position: Int) {
        holder.textViewTextHead.text = list[position].textHead
        holder.imageViewBackImage.setImageDrawable(
            App.getApplicationContext()
                .getDrawable(list[position].imgId)
        )
    }

    fun replaceAll(data: ArrayList<PlacesModel>) {
        list = data
        notifyDataSetChanged()
    }
}