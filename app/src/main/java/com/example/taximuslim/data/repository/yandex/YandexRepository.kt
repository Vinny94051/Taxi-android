package com.example.taximuslim.data.repository.yandex

import com.example.taximuslim.data.network.api.YandexApi
import com.example.taximuslim.data.network.dto.Token
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentRequest
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentResponse
import com.example.taximuslim.data.network.dto.yandex.cashbox.SentIdPayRequest
import io.reactivex.Single
import javax.inject.Inject

class YandexRepository @Inject constructor(var yandexApi: YandexApi, var token: Token) :
    IYandexRepository {

    override fun makePayment(payment: PaymentRequest): Single<PaymentResponse> =
        yandexApi.makePayment(token.token, payment)

    override fun sentPayId(payId: SentIdPayRequest): Single<PaymentResponse> =
        yandexApi.sentIdPay(token.token, payId)
}