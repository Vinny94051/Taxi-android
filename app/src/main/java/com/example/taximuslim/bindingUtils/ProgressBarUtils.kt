package com.example.taximuslim.bindingUtils

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.example.taximuslim.presentation.view.auth.driver.LoadingStatus

@BindingAdapter("loading_status")
fun ProgressBar.setBackGround(status: LoadingStatus) {
    when (status){
        LoadingStatus.NULL -> this.visibility = View.GONE
        LoadingStatus.LOADING -> this.visibility = View.VISIBLE
        LoadingStatus.ERROR -> this.visibility = View.GONE
        LoadingStatus.COMPLETE -> this.visibility = View.GONE
    }
}


