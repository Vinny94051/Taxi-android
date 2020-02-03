package com.example.taximuslim.data.network.dto.google.directions

import com.google.gson.annotations.SerializedName




data class Arrival_time (

	@SerializedName("text") val text : String,
	@SerializedName("time_zone") val time_zone : String,
	@SerializedName("value") val value : Int
)