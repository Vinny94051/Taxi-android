package com.example.taximuslim.data.repository.yandex

import com.example.taximuslim.data.network.api.YandexApi
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentRequest
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentResponse
import io.reactivex.Single
import javax.inject.Inject

class YandexRepository @Inject constructor(var yandexApi: YandexApi) : IYandexRepository {

    override fun makePayment(payment: PaymentRequest): Single<PaymentResponse> =
        yandexApi.makePayment(payment)
}