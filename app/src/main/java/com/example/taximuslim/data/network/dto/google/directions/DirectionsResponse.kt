package com.example.taximuslim.data.network.dto.google.directions

import com.google.gson.annotations.SerializedName



data class DirectionsResponse (

    @SerializedName("geocoded_waypoints") val geocoded_waypoints : List<Geocoded_waypoints>,
    @SerializedName("routes") val routes : List<Routes>,
    @SerializedName("status") val status : String
)