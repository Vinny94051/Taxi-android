package com.example.taximuslim.data.network.remote.request.driver

import com.google.gson.annotations.SerializedName

class ChangePhoneRequest(
    @SerializedName("phone")
    val phone: String
)

