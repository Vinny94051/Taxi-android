package com.example.taximuslim.data.network.remote.response.googleMapDirectionResponse

data class MapDirectionResponse(
    val geocoded_waypoints: List<GeocodedWaypoint>,
    val routes: List<Route>,
    val status: String
)