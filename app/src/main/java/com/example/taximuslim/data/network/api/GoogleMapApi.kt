package com.example.taximuslim.data.network.api

import com.example.taximuslim.data.network.dto.google.directions.DirectionsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleMapApi {

    @GET("directions/json?")
     fun getDirection(
        @Query("origin") start: String,
        @Query("destination") end: String,
        @Query("key") apiKey: String
    ): Call<DirectionsResponse>

}