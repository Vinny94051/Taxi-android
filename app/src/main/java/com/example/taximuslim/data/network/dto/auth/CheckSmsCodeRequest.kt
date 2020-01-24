package com.example.taximuslim.data.network.dto.auth

import com.google.gson.annotations.SerializedName

data class CheckSmsCodeRequest(
    @SerializedName("phone")
    val phoneNumber : String,
    @SerializedName("code")
    val code : String
)