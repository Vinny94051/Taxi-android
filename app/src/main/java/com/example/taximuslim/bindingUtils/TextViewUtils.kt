package com.example.taximuslim.bindingUtils

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.driver.LoadingStatus
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SetTextI18n")
@BindingAdapter("price")
fun TextView.setPrice(price: String) {
    this.text = "${price} RUB"
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
    carClass?.let {
        this.text = this.context.getString(R.string.car_class, carClass)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("delete_status")
fun TextView.setDeleteTrigger(status: LoadingStatus) {
    visibility = if (status == LoadingStatus.COMPLETE) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("take_status")
fun TextView.setTakeTrigger(status: LoadingStatus) {
    visibility = if ((status == LoadingStatus.ERROR) || (status == LoadingStatus.NULL)) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("date")
fun TextView.setDate(dateAndTime: String) {
    val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(dateAndTime)
    val formatter = SimpleDateFormat("dd MMMM HH:mm", Locale.getDefault())
    text = formatter.format(date)

}

