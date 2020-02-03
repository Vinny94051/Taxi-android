package com.example.taximuslim.bindingUtils

import android.widget.CheckBox
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

@BindingAdapter("bindText")
fun EditText.setText(data: String) {
    setText(data)
}

@InverseBindingAdapter(attribute = "bindText")
fun EditText.getSelected(): String {
    return text.toString()
}
