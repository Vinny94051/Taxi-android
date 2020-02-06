package com.example.taximuslim.data.network.dto.guide

import com.google.gson.annotations.SerializedName

data class PlaceByLocationResponse(
    @SerializedName("id")
    val placeId : Int,
    @SerializedName("category")
    val category : String,
    @SerializedName("name")
    val name : String,
    @SerializedName("text")
    val text : String,
    @SerializedName("address")
    val address : String,
    @SerializedName("lat")
    val latitude : Double,
    @SerializedName("lng")
    val longitude : Double,
    @SerializedName("image")
    val imageUrl : String,
    @SerializedName("distance")
    val distance : Double
)