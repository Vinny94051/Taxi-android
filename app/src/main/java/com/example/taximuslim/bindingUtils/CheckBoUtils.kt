package com.example.taximuslim.bindingUtils

import android.widget.CheckBox
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

@BindingAdapter("bindSelected")
fun CheckBox.setSelcted(data: Boolean) {
    isSelected = data
}

@InverseBindingAdapter(attribute = "bindSelected")
fun CheckBox.getSelected(): Boolean {
    return isSelected
}
