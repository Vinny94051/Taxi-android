package com.example.taximuslim.data.network.dto.google.directions

import com.google.gson.annotations.SerializedName

data class Agencies (
	@SerializedName("name") val name : String,
	@SerializedName("url") val url : String
)