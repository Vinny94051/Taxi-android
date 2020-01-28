package com.example.taximuslim.data.network.dto.order

import com.google.gson.annotations.SerializedName

data class TariffRequest(
    @SerializedName("country")
    val country : String,
    @SerializedName("city")
    val city : String
)