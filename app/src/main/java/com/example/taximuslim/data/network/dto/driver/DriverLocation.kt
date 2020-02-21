package com.example.taximuslim.data.network.dto.driver

import com.google.gson.annotations.SerializedName

data class DriverLocation(
    @SerializedName("lat")
    val latitude : String,
    @SerializedName("lng")
    val longitude : String

)