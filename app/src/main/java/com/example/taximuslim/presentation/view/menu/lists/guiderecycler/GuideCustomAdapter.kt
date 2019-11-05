package com.example.taximuslim.presentation.view.menu.lists.guiderecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taximuslim.App
import com.example.taximuslim.R

class GuideCustomAdapter(private var list: ArrayList<GuideModel> = arrayListOf()) :
    RecyclerView.Adapter<GuideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GuideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.guide_recycle_list,
                parent, false
            )
        )


    override fun getItemCount()= list.size

    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {
        holder.img.setImageDrawable(App.getApplicationContext().getDrawable(list[position].imgId))
        holder.textHead.text = list[position].textHead
    }

    fun replaceAll(data: ArrayList<GuideModel>) {
        list = data
        notifyDataSetChanged()
    }
}