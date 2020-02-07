package com.example.taximuslim.bindingUtils

import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.example.taximuslim.domain.models.driver.auth.CarMark
import com.example.taximuslim.domain.models.driver.auth.CarModel
import com.example.taximuslim.domain.models.driver.auth.CarColor

@BindingAdapter("list")
fun Spinner.connectColor(list: List<CarColor>?) {
    list?.let { list ->
        val adapter = ArrayAdapter<String>(
            this.context,
            android.R.layout.simple_spinner_item,
            list.map { it.name })
        this.adapter = adapter
    }

}

@BindingAdapter("list")
fun Spinner.connectMark(list: List<CarMark>?) {
    list?.let { list ->
        val adapter = ArrayAdapter<String>(
            this.context,
            android.R.layout.simple_spinner_item,
            list.map { it.name })
        this.adapter = adapter
    }

}


@BindingAdapter("list")
fun Spinner.connectModel(list: List<CarModel>?) {
    list?.let { list ->
        val adapter = ArrayAdapter<String>(
            this.context,
            android.R.layout.simple_spinner_item,
            list.map { it.name })
        this.adapter = adapter
    }

}