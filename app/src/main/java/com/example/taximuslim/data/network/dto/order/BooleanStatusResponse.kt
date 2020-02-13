package com.example.taximuslim.data.network.dto.order

import com.google.gson.annotations.SerializedName

data class BooleanStatusResponse(
    @SerializedName("status")
    val status : String
)