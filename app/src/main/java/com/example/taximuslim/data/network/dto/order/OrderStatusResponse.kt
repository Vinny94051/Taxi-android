package com.example.taximuslim.data.network.dto.order

import com.google.gson.annotations.SerializedName

data class OrderStatusResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("phone_client")
    val clientPhone: String,
    @SerializedName("phone_driver")
    val driverPhone: String,
    @SerializedName("name_client")
    val clientName: String,
    @SerializedName("name_driver")
    val driverName: String,
    @SerializedName("start_adress")
    val startPointAddress: String,
    @SerializedName("start_lat")
    val startPointLatitude: Double,
    @SerializedName("start_lgn")
    val startPointLongitude: Double,
    @SerializedName("end_adress")
    val endPointAddress: String,
    @SerializedName("end_lat")
    val endPointLatitude: Double,
    @SerializedName("end_lgn")
    val endPointLongitude: Double,
    @SerializedName("position_driver_lat")
    val driverPositionLatitude: Double?,
    @SerializedName("position_driver_lng")
    val driverPositionLongitude: Double?,
    @SerializedName("price")
    val price: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("time")
    val time: String,
    @SerializedName("time_to_get")
    val timeToGet: String?,
    @SerializedName("auto")
    val car: String,
    @SerializedName("reply_client")
    val clientReply: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("request")
    val driversList: List<Driver>
)

data class Driver(
    @SerializedName("id_driver")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("car")
    val car: String
)