package com.example.taximuslim.domain.models.driver

import com.example.taximuslim.domain.models.map.PathModel

data class DriverOrderModel(
    val startAddress: String,
    val endAddress: String,
    val comment: String,
    val price: String,
    val distance: String,
    val travelTime: String,


    val path: PathModel
)