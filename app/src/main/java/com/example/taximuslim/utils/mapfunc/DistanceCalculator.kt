package com.example.taximuslim.utils.mapfunc

import android.location.Location
import com.example.taximuslim.App
import com.example.taximuslim.utils.toLocation
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class DistanceCalculator {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var addressIntentService: FetchAddressIntentService

    private fun createLocationFromAddress(address: String): Location? =
        addressIntentService.getLocationFromAddress(address)?.toLocation()

    fun calculateDistanceBetweenTwoAddresses(address1: String, address2: String): Float {
        val pointA = createLocationFromAddress(address1)
        val pointB = createLocationFromAddress(address2)

        pointA?.distanceTo(pointB)?.let { distanceInMts ->
            return distanceInMts / 1000
        }
        return 0f
    }
}