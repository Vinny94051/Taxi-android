package com.example.taximuslim.domain.models.driver

import com.example.taximuslim.domain.models.map.PathModel

data class DriverOrderHistoryModel(
    val startAddress: String,
    val endAddress: String,
    val price: String,
    val date: String,

    val path: PathModel
)