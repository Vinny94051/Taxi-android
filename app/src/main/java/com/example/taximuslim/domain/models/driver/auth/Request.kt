package com.example.taximuslim.domain.models.driver.auth

data class Request(
    val car: String,
    val id_driver: Int,
    val lat: Double,
    val lng: Double,
    val name: String,
    val time: String
)