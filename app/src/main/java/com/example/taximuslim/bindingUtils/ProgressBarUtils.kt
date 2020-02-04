package com.example.taximuslim.bindingUtils

import android.view.View
import android.widget.CheckBox
import android.widget.ProgressBar
import androidx.cardview.widget.CardView
import androidx.core.graphics.drawable.DrawableCompat.setTint
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.driver.LoadingImageStatus

@BindingAdapter("loading_status")
fun ProgressBar.setBackGround(status: LoadingImageStatus) {
    when (status){
        LoadingImageStatus.NULL -> this.visibility = View.GONE
        LoadingImageStatus.LOADING -> this.visibility = View.VISIBLE
        LoadingImageStatus.ERROR -> this.visibility = View.GONE
        LoadingImageStatus.COMPLETE -> this.visibility = View.GONE
    }
}


