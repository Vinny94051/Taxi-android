package com.example.taximuslim.data.network.dto.order

import com.google.gson.annotations.SerializedName

data class ChooseDriverRequest(
    @SerializedName("id_trip")
    val tripId : Int,
    @SerializedName("id_driver")
    val driverId : Int
)