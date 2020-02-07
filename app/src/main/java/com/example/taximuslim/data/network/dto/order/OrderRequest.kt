package com.example.taximuslim.data.network.dto.order

import com.google.gson.annotations.SerializedName

data class OrderRequest(
    @SerializedName("start_adress")
    val startPointAddress : String,
    @SerializedName("start_lat")
    val startPointLat : String,
    @SerializedName("start_lgn")
    val startPointLng: String,
    @SerializedName("end_adress")
    val endPointAddress : String,
    @SerializedName("end_lat")
    val endPointLat : String,
    @SerializedName("end_lgn")
    val endPointLng : String,
    @SerializedName("tariff")
    val tariff : Int,
    @SerializedName("price")
    val price : Int,
    @SerializedName("comment")
    val comment : String,
    @SerializedName("type_payment")
    val paymentType : Int
)