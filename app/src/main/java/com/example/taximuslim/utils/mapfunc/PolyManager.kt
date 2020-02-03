package com.example.taximuslim.utils.mapfunc

import android.graphics.Color
import com.example.taximuslim.domain.models.google.Route
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions

class PolyManager(private var mMap: GoogleMap) {

    companion object {
        var line: Polyline? = null
    }

    fun drawRoute(route: Route, startPoint: LatLng, endPoint: LatLng) {
        val currentRoute = PolylineOptions()
            .apply {
                width(20f)
                color(Color.GREEN)
                add(startPoint)
                for (point in route.steps) add(point)
                add(endPoint)
            }
        if (line != null)
            line?.remove()

            line = mMap.addPolyline(currentRoute)
    }

}