package com.example.taximuslim.utils.location

import android.location.Location

interface IUserLocationProvider {
    /**
     * Return current user location
     * @param listener return this location
     */
    fun getLocation(listener: (Location?) -> Unit)
}