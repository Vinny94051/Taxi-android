package com.example.taximuslim.bindingUtils

import android.widget.CheckBox
import androidx.cardview.widget.CardView
import androidx.core.graphics.drawable.DrawableCompat.setTint
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.driver.LoadingStatus

@BindingAdapter("correct")
fun CardView.setCorrectColor(correct: Boolean?) {
    if (correct != null){
        background.setTint(
            if (correct) context.getColor(R.color.colorThemeGreen)
            else context.getColor(R.color.red)
        )
    }
}

@BindingAdapter("status")
fun CardView.setCorrectColor(status: LoadingStatus) {
    background.setTint(
    when (status){
        LoadingStatus.COMPLETE -> context.getColor(R.color.colorAccent)
        LoadingStatus.ERROR -> context.getColor(R.color.red)
        else -> context.getColor(android.R.color.white)
    }
    )
}


