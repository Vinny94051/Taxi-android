package com.example.taximuslim.domain.models.driver.order

import com.example.taximuslim.domain.models.driver.auth.Request

data class OrderHistory(
    val auto: String,
    val comment: String,
    val date: String,
    val distance: Double,
    val endAddress: String,
    val end_lat: Double,
    val end_lgn: Double,
    val id: Int,
    val name_client: String,
    val name_driver: String,
    val phone_client: String,
    val phone_driver: String,
    val position_driver_lat: Any,
    val position_driver_lng: Any,
    val price: String,
    val reply_client: String,
    val request: List<Request>,
    val startAddress: String,
    val start_lat: Double,
    val start_lgn: Double,
    val status: Int,
    val time: String,
    val time_to_get: Any
)