package com.example.taximuslim.data.network.remote.response.driver

import com.google.gson.annotations.SerializedName

data class OrderResponse (
    val id: Int,
    @SerializedName("name_client")
    val clientName: String,
    @SerializedName("start_adress")
    val startAddress: String,
    @SerializedName("start_lat")
    val startLat: String,
    @SerializedName("start_lgn")
    val startLgn: String,
    @SerializedName("end_adress")
    val endAddress: String,
    @SerializedName("end_lat")
    val endLat: String,
    @SerializedName("end_lgn")
    val endLgn: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("distance")
    val distance: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("time")
    val time: String
)