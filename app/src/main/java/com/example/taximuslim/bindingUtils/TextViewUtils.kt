package com.example.taximuslim.bindingUtils

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.driver.LoadingImageStatus

@SuppressLint("SetTextI18n")
@BindingAdapter("price")
fun TextView.setPrice(price: String) {
    this.text = "${price}RUB"
}

@SuppressLint("SetTextI18n")
@BindingAdapter("time")
fun TextView.setTime(time: String) {
    this.text = "$time минут"
}

@SuppressLint("SetTextI18n")
@BindingAdapter("distance")
fun TextView.setDistance(distance: String) {
    this.text = "$distance км"
}

@SuppressLint("SetTextI18n")
@BindingAdapter("car_class")
fun TextView.setCarClass(carClass: String?) {
    carClass?.let{
        this.text = this.context.getString(R.string.car_class, carClass)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("delete_status")
fun TextView.setDeleteTrigger(status: LoadingImageStatus) {
    if (status == LoadingImageStatus.COMPLETE){
        visibility = View.VISIBLE
    }else{
        visibility = View.GONE
    }
}

