package com.example.taximuslim.data.network.dto.order

import com.google.gson.annotations.SerializedName

data class OrderStatusResponse (
    @SerializedName("status")
    val status: Int,
    @SerializedName("request")
    val driversList : List<Driver>
)

data class Driver(
    @SerializedName("id_driver")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("car")
    val car : String
)