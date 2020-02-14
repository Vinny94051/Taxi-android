package com.example.taximuslim.data.network.dto.driver

import com.google.gson.annotations.SerializedName

data class PaymentResult(
    @SerializedName("payment_token")
    val paymentToken : String,
    @SerializedName("value")
    val price : String
)