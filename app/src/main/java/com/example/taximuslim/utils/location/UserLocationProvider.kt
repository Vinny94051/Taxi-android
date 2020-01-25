package com.example.taximuslim.utils.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import javax.inject.Inject

class UserLocationProvider @Inject constructor(private val context: Context): IUserLocationProvider {

    @SuppressLint("MissingPermission")
    override fun getLocation(listener: (Location?) -> Unit) {
        LocationServices.getFusedLocationProviderClient(context)
            .lastLocation.addOnSuccessListener { location ->
            listener.invoke(location)
        }
    }

}