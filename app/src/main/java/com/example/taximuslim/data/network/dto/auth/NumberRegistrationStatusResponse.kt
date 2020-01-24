package com.example.taximuslim.data.network.dto.auth

import com.google.gson.annotations.SerializedName

data class NumberRegistrationStatusResponse(
    @SerializedName("status")
    val registrationStatusResponse: String
)