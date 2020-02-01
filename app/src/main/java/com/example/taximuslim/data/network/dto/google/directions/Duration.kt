package com.example.taximuslim.data.network.dto.google.directions

import com.google.gson.annotations.SerializedName


data class Duration(

    @SerializedName("text") val text: String,
    @SerializedName("value") val value: Int
)