package com.example.taximuslim.domain.order.models

data class OrderModel(
    val startPointAddress : String,
    val startPointLat : Double,
    val startPointLng: Double,
    val endPointAddress : String,
    val endPointLat : Double,
    val endPointLng : Double,
    val tariff : Int,
    val price : Int,
    val comment : String,
    val paymentType : Int
)