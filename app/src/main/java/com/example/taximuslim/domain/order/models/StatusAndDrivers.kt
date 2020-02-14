package com.example.taximuslim.domain.order.models

data class StatusAndDrivers(
    val id: Int,
    val clientPhone: String,
    val driverPhone: String,
    val clientName: String,
    val driverName: String,
    val startPointAddress: String,
    val startPointLatitude: Double,
    val startPointLongitude: Double,
    val endPointAddress: String,
    val endPointLatitude: Double,
    val endPointLongitude: Double,
    val driverPositionLatitude: Double,
    val driverPositionLongitude: Double,
    val price: String,
    val comment: String,
    val distance: Double,
    val time: String,
    val timeToGet: String,
    val car: String,
    val clientReply: String,
    val date: String,
    val status: Int,
    val drivers: List<DriverModel>
)

data class DriverModel(
    val id: Int,
    val name: String,
    val car: String
)

