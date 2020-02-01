package com.example.taximuslim.data.network.dto.google.directions

import com.google.gson.annotations.SerializedName



data class OverviewPolyline (

	@SerializedName("points") val points : String
)