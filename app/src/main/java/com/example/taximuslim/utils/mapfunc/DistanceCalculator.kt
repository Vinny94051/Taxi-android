package com.example.taximuslim.utils.mapfunc

import android.location.Location
import com.example.taximuslim.App
import com.example.taximuslim.utils.toLocation
import javax.inject.Inject

class DistanceCalculator {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var addressIntentService: MapManager

    private fun createLocationFromAddress(address: String): Location? =
        addressIntentService.getLocationFromAddress(address).toLocation()

    fun calculateDistanceBetweenTwoAddresses(address1: String, address2: String): Float {
        val pointA = createLocationFromAddress(address1)
        val pointB = createLocationFromAddress(address2)

        pointA?.distanceTo(pointB)?.let { distanceInMts ->
            return distanceInMts / 1000
        }
        return 0f
    }
}