package com.example.taximuslim.bindingUtils

import android.widget.CheckBox
import androidx.databinding.BindingAdapter
import com.example.taximuslim.domain.models.map.PathModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.PolylineOptions

@BindingAdapter("path")
fun MapView.setPath(path: PathModel) {
    this.getMapAsync {map ->
        val polyLine = map.addPolyline(PolylineOptions().also{it.ad
        })
    }
}