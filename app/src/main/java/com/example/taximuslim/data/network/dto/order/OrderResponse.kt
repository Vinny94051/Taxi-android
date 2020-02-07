package com.example.taximuslim.data.network.dto.order

import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("id_trip")
    val tripId : Int
)