package com.example.taximuslim.data.network.api

import com.example.taximuslim.data.network.dto.order.TariffsResponse
import com.example.taximuslim.data.network.dto.order.TrafficRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface OrderAPi {
    @GET("tariff/view-tariff")
    fun getTariffs(
        @Header("Authorization") token : String,
        @Body location : TrafficRequest
    ) : Call<TariffsResponse>
}