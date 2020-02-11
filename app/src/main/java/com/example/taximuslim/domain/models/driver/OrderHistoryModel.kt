package com.example.taximuslim.domain.models.driver

import com.example.taximuslim.domain.models.driver.auth.Request
import com.example.taximuslim.domain.models.google.Route

data class  OrderHistoryModel(
    val auto: String,
    val comment: String,
    val date: String,
    val distance: Double,
    val endAddress: String,
    val startLat: Double,
    val startLgn: Double,
    val endLat: Double,
    val endLng: Double,
    val id: Int,
    val clientName: String,
    val driverName: String,
    val clientPhone: String,
    val driverPhone: String,
    val positionDriverLat: Any,
    val positionDriverLng: Any,
    val price: String,
    val clientReply: String,
    val request: List<Request>,
    val startAddress: String,
    val status: Int,
    val time: String,
    val timeToGet: String?,
    var route: Route? = null
)