package com.example.taximuslim.presentation.view.adapter.guide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.viewholder.BaseViewHolder
import com.example.taximuslim.domain.models.guide.PlaceByLocationModel
import kotlinx.android.synthetic.main.gradient_button.view.*
import kotlinx.android.synthetic.main.item_guide_places_list.view.*

class PlacesViewHolder private constructor(itemView : View) : BaseViewHolder(itemView) {

    companion object{
        fun from(parent : ViewGroup)=
            PlacesViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_guide_places_list ,
                    parent, false
                )
            )
    }

    private val placeImage : ImageView = itemView.placeImageView
    private val placeType : TextView = itemView.placeTypeTextView
    private val addressText : TextView = itemView.placeAddressTextView
    private val distanceText : TextView = itemView.distanceTextView
    private var onItemClickListener : ((Int) -> Unit)? = null
    private var buttonText : TextView = itemView.main_btn_text



    override fun <T> bindView(item: T) {

        buttonText.text = "Заказать"

         (item as PlaceByLocationModel).apply {
             distanceText.text = "$distance км"
             addressText.text = address
             placeType.text = category
             Glide.with(placeImage.context)
                 .load(imageUrl)
                 .transform(CenterCrop(), RoundedCorners(16))
                 .into(placeImage)

             itemView.rootView.setOnClickListener {
                 onItemClickListener?.invoke(adapterPosition)
             }
         }


    }

    fun setOnItemClickListener(listener : ((Int) -> Unit)){
        onItemClickListener = listener
    }
}