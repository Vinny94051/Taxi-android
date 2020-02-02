package com.example.taximuslim.utils.mapfunc

import com.google.android.gms.maps.model.LatLng

interface IDecodePoly {
    fun decodePoly(encoded: String): List<LatLng>
}