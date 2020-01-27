package com.example.taximuslim.bindingUtils

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter

@SuppressLint("SetTextI18n")
@BindingAdapter("price")
fun TextView.setPrice(price: String) {
    this.text = "${price}RUB"
}