package com.example.taximuslim.data.network.dto.guide

import com.google.gson.annotations.SerializedName

data class PlaceByLocationRequest(
    @SerializedName("id")
    val placeId : Int,
    @SerializedName("lat")
    val latitude : Double,
    @SerializedName("lng")
    val longitude : Double
)