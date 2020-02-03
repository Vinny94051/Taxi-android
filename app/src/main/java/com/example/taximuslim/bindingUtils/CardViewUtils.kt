package com.example.taximuslim.bindingUtils

import android.widget.CheckBox
import androidx.cardview.widget.CardView
import androidx.core.graphics.drawable.DrawableCompat.setTint
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.example.taximuslim.R

@BindingAdapter("correct")
fun CardView.setCorrectColor(correct: Boolean?) {
    if (correct != null){
        background.setTint(
            if (correct) context.getColor(R.color.colorThemeGreen)
            else context.getColor(R.color.red)
        )
    }
}


