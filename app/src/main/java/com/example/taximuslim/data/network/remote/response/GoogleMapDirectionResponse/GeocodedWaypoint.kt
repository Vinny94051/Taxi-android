package com.example.taximuslim.data.network.remote.response.GoogleMapDirectionResponse

data class GeocodedWaypoint(
    val geocoder_status: String,
    val place_id: String,
    val types: List<String>
)