package com.example.taximuslim.utils.mapfunc

import android.content.Context
import android.graphics.Color
import com.example.taximuslim.App
import com.example.taximuslim.R
import com.example.taximuslim.domain.models.google.Route
import com.example.taximuslim.utils.isNotEmpty
import com.example.taximuslim.utils.toPx
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import javax.inject.Inject

class PolyManager(private var mMap: GoogleMap) {

    init {
        App.appComponent.inject(this)
    }

    companion object {
        var line: Polyline? = null
    }

    @Inject
    lateinit var addressIntentService: MapManager

    @Inject
    lateinit var context : Context

    fun drawRoute(route: Route, startPoint: LatLng, endPoint: LatLng) {
        val currentRoute = PolylineOptions()
            .apply {
                width(10f)
                color(context.resources.getColor(R.color.colorThemeGreen))
                for (point in route.steps) add(point)
            }
        if (line != null)
            line?.remove()


        if (MapManager.markerPointALocation.isNotEmpty())
            MapManager.markerPointALocation?.remove()


        if (MapManager.markerPointBLocation.isNotEmpty())
            MapManager.markerPointBLocation?.remove()

        MapManager.markerPointALocation =
            addressIntentService.addMarker(mMap, route.steps[0], R.drawable.start_route_marker)
        MapManager.markerPointBLocation =
            addressIntentService.addMarker(mMap, route.steps[route.steps.size-1], R.drawable.brown_marker)
        addressIntentService.moveCameraToTwoMarkers(mMap)

        line = mMap.addPolyline(currentRoute)
    }

}