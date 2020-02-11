package com.example.taximuslim.data.network.api

import android.database.Observable
import com.example.taximuslim.data.network.dto.order.*
import io.reactivex.Single
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
    ): OrderResponse

    @POST("trip/trip-order-client-status")
    fun fetchOrderStatus(
        @Header("Authorization") token: String,
        @Body  statusRequest : StatusRequest
    ) : Single<OrderStatusResponse>
}