package com.example.taximuslim.data.network.dto.yandex.cashbox

import com.google.gson.annotations.SerializedName

data class SentIdPayRequest(
    @SerializedName("id_pay")
    val payId : String
)