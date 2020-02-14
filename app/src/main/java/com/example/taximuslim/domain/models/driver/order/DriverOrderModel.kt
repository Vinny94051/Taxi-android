package com.example.taximuslim.domain.models.driver.order

data class DriverOrderModel (
    val id: Int,
    val clientName: String,
    val startAddress: String,
    val startLat: String,
    val startLgn: String,
    val endAddress: String,
    val endLat: String,
    val endLgn: String,
    val price: String,
    val comment: String,
    val distance: String,
    val date: String,
    val time: String
)