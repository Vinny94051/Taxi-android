package com.example.taximuslim.data.network.dto.google.directions

import com.google.gson.annotations.SerializedName


data class Bounds(

    @SerializedName("northeast") val northeast: Northeast,
    @SerializedName("southwest") val southwest: Southwest
)