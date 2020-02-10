package com.example.taximuslim.data.network.remote.response.driver


import com.google.gson.annotations.SerializedName

data class DriverIncome(
    @SerializedName("earnings_day")
    val earningsDay: String,
    @SerializedName("earnings_month")
    val earningsMonth: String,
    @SerializedName("earnings_trip")
    val earningsTrip: String,
    @SerializedName("earnings_week")
    val earningsWeek: String
)