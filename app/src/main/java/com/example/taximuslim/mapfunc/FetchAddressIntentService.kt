package com.example.taximuslim.mapfunc

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.*

class FetchAddressIntentService(private val context: Context) {

    fun latLngToAddress(location: LatLng): String {
        var address = "Cannot define your address."
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addresses: List<Address> =
                geocoder.getFromLocation(location.latitude, location.longitude, 1)
            address = addresses[0].getAddressLine(0)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return address
    }

    fun addMarkerAndMoveCameraToIt(mMap: GoogleMap, location: LatLng, zoom: Float) {
        mMap.addMarker(MarkerOptions().position(location))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(zoom))
    }

}