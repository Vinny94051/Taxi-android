package com.example.taximuslim.bindingUtils

import androidx.databinding.BindingAdapter
import com.example.taximuslim.domain.models.google.Route
import com.example.taximuslim.utils.mapfunc.PolyManager
import com.google.android.gms.maps.MapView

@BindingAdapter("route")
fun MapView.setPath(path: Route?) {
    path?.let{path ->
        this.getMapAsync {map ->
            PolyManager(map).drawRoute(path)
        }
    }

}