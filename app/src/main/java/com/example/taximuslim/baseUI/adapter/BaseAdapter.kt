package com.example.taximuslim.baseUI.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder
    abstract override fun getItemCount(): Int
    abstract override fun onBindViewHolder(holder: BaseViewHolder, position: Int)
}