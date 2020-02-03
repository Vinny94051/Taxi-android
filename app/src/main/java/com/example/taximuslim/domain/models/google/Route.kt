package com.example.taximuslim.domain.models.google

import com.google.android.gms.maps.model.LatLng

data class Route (
    val steps : List<LatLng>
)
