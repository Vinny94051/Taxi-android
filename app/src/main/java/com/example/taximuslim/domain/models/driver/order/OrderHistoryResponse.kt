package com.example.taximuslim.domain.models.driver.order

import com.example.taximuslim.domain.models.driver.auth.Request
import com.google.gson.annotations.SerializedName

data class OrderHistoryResponse(
    val auto: String,
    val comment: String,
    val date: String,
    val distance: Double,
    @SerializedName("start_adress")
    val startAddress: String,
    @SerializedName("end_adress")
    val endAddress: String,
    @SerializedName("start_lat")
    val startLat: Double,
    @SerializedName("start_lgn")
    val startLgn: Double,
    @SerializedName("end_lat")
    val endLat: Double,
    @SerializedName("end_lgn")
    val endLng: Double,
    val id: Int,
    @SerializedName("name_client")
    val clientName: String,
    @SerializedName("name_driver")
    val driverName: String,
    @SerializedName("phone_client")
    val clientPhone: String,
    @SerializedName("phone_driver")
    val driverPhone: String,
    @SerializedName("position_driver_lat")
    val positionDriverLat: Any,
    @SerializedName("position_driver_lng")
    val positionDriverLng: Any,
    val price: String,
    @SerializedName("reply_client")
    val clientReply: String,
    val request: List<Request>,
    val status: Int,
    val time: String,
    @SerializedName("time_to_get")
    val timeToGet: Any
)