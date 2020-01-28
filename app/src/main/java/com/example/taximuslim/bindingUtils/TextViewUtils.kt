package com.example.taximuslim.bindingUtils

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter

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