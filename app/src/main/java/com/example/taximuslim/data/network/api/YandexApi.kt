package com.example.taximuslim.data.network.api

import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentRequest
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface YandexApi {
    @POST("payments")
    fun makePayment(
        @Body payment : PaymentRequest
    ) : Single<PaymentResponse>
}