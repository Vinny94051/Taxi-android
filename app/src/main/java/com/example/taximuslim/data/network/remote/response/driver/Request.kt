package com.example.taximuslim.data.network.remote.response.driver

data class Request(
    val car: String,
    val id_driver: Int,
    val lat: Double,
    val lng: Double,
    val name: String,
    val time: String
)