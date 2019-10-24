package com.example.taximuslim.utils.location

import android.annotation.SuppressLint
import android.location.Location
import com.example.taximuslim.App
import com.google.android.gms.location.LocationServices

class MyLocationProvider {

    companion object{
        @SuppressLint("MissingPermission")
        fun getLocation(listener: (Location) -> Unit) {
            LocationServices.getFusedLocationProviderClient(App.getApplicationContext())
                .lastLocation.addOnSuccessListener { location ->
                location?.let {
                    listener.invoke(it)
                }
            }
        }
    }
}