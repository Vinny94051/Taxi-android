package com.example.taximuslim.data.network.dto.order

import com.google.gson.annotations.SerializedName

data class TariffsResponse(
    @SerializedName("economy")
    val economy: Int,
    @SerializedName("comfort")
    val comfort: Int,
    @SerializedName("business")
    val business : Int
)