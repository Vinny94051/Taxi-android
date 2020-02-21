package com.example.taximuslim.data.network.dto.driver

import com.google.gson.annotations.SerializedName

data class FetchDriverStatusRequest(
    @SerializedName("id_trip")
    val tripId : Int,
    @SerializedName("position_driver_lat")
    val driverLatitude : String,
    @SerializedName("position_driver_lng")
    val driverLongitude : String
)