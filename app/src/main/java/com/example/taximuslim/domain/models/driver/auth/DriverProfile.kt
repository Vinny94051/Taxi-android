package com.example.taximuslim.domain.models.driver.auth

data class DriverProfile(
    val car: String,
    val id_driver: Int,
    val lat: String,
    val lng: String,
    val name: String,
    val time: String
)