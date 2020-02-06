package com.example.taximuslim.utils.mapfunc

import com.google.android.gms.maps.model.LatLng

interface IDecodePoly {
    /**
     * @param encoded
     * Coded string by Directions API with route
     * from point A to point B
     *
     * This method decode encoded string into List of LatLng objects
     */
    fun decodePoly(encoded: String): List<LatLng>
}