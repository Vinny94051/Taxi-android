package com.example.taximuslim.presentation.view.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taximuslim.App
import com.example.taximuslim.R

class MapsCustomAdapter(private var list: ArrayList<PlacesModel> = arrayListOf()) :
    RecyclerView.Adapter<MapsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapsViewHolder =
        MapsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.place_recycle_list,
                parent, false
            )
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MapsViewHolder, position: Int) {
        holder.textViewTextHead.text = list[position].textHead
        holder.imageViewBackImage.setImageDrawable(App.getApplicationContext()
            .getDrawable(list[position].imgId))
    }

    fun replaceAll(data : ArrayList<PlacesModel>){
        list = data
        notifyDataSetChanged()
    }
}