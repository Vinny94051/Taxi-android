package com.example.taximuslim.data.network.dto

import com.google.gson.annotations.SerializedName

data class NumberRequest(
    @SerializedName("phone")
    val phoneNumber : String
)