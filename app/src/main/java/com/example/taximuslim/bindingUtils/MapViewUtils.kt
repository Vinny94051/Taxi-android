package com.example.taximuslim.bindingUtils

import android.widget.CheckBox
import androidx.databinding.BindingAdapter
import com.example.taximuslim.domain.models.google.Route
import com.example.taximuslim.domain.models.map.PathModel
import com.example.taximuslim.utils.mapfunc.PolyManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.PolylineOptions

@BindingAdapter("route")
fun MapView.setPath(path: Route?) {
    this.getMapAsync {map ->
        val manager = PolyManager(map).drawRoute(path)
    }
}