package com.example.taximuslim.data.network.dto.guide

import com.google.gson.annotations.SerializedName

data class GuideCategoryResponse(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("image")
    val imageUrl : String
)