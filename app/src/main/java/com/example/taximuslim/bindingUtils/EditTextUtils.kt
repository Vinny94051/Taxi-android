package com.example.taximuslim.bindingUtils

import android.content.res.ColorStateList
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.driver.LoadingStatus

@BindingAdapter("bindText")
fun EditText.setText(data: String) {
    setText(data)
}

@InverseBindingAdapter(attribute = "bindText")
fun EditText.getSelected(): String {
    return text.toString()
}

@BindingAdapter("status")
fun EditText.setText(status: LoadingStatus) {
    val color = (when (status){
        LoadingStatus.ERROR -> R.color.red
        LoadingStatus.COMPLETE -> R.color.colorAccent
        else -> R.color.colorLightGrey
    })

    DrawableCompat.setTint(this.background, ContextCompat.getColor(context, color))
}

