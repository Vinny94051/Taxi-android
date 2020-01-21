package com.example.taximuslim.data.network.dto.auth

import com.google.gson.annotations.SerializedName

data class PreseptResponse(
    @SerializedName("text")
    val text : String,
    @SerializedName("image")
    val backgroundImage : String
)