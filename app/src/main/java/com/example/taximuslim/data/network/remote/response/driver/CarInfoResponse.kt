package com.example.taximuslim.data.network.remote.response.driver

data class CarInfoResponse(
    val id: Int,
    val id_car: Int,
    val id_car_model: Int,
    val id_color: Int,
    val id_image_car: Int,
    val id_image_car_registration: Int,
    val number: String
)