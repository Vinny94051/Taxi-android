package com.example.taximuslim.data.network.api

import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentRequest
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentResponse
import com.example.taximuslim.data.network.dto.yandex.cashbox.SentIdPayRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface YandexApi {
    @POST("money/pay")
    fun makePayment(
        @Header("Authorization") token: String,
        @Body payment : PaymentRequest
    ) : Single<PaymentResponse>

    @POST("money/pay-status")
    fun sentIdPay(
        @Header("Authorization") token: String,
        @Body sentIdPayRequest : SentIdPayRequest
    ) : Single<PaymentResponse>
}