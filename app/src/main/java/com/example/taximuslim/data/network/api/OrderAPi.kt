package com.example.taximuslim.data.network.api

import com.example.taximuslim.data.network.dto.order.OrderRequest
import com.example.taximuslim.data.network.dto.order.OrderResponse
import com.example.taximuslim.data.network.dto.order.TariffsResponse
import com.example.taximuslim.data.network.dto.order.TariffRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface OrderAPi {
    @POST("tariff/view-tariff")
    fun getTariffs(
        @Header("Authorization") token: String,
        @Body location: TariffRequest
    ): Call<TariffsResponse>

    @POST("order/add-order")
    suspend fun createOrder(
        @Header("Authorization") token: String,
        @Body orderRequest: OrderRequest
    ) : OrderResponse
}