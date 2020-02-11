package com.example.taximuslim.domain.order.models

data class StatusAndDrivers(
    val status : Int,
    val drivers : List<DriverModel>
)

data class DriverModel(
    val id : Int,
    val name : String,
    val car : String
)

