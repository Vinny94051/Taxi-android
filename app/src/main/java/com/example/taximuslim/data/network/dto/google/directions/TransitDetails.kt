package com.example.taximuslim.data.network.dto.google.directions

import com.google.gson.annotations.SerializedName


data class TransitDetails(

    @SerializedName("arrival_stop") val arrival_stop: ArrivalStop,
    @SerializedName("arrival_time") val arrival_time: Arrival_time,
    @SerializedName("departure_stop") val departure_stop: DepartureStop,
    @SerializedName("departure_time") val departure_time: Departure_time,
    @SerializedName("headsign") val headsign: String,
    @SerializedName("line") val line: Line,
    @SerializedName("num_stops") val num_stops: Int,
    @SerializedName("trip_short_name") val trip_short_name: String
)