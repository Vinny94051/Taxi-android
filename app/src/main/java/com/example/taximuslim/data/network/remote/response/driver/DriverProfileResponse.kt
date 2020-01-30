package com.example.taximuslim.data.network.remote.response.driver

data class DriverProfileResponse(
    val car: String,
    val id_driver: Int,
    val lat: String,
    val lng: String,
    val name: String,
    val time: String
)