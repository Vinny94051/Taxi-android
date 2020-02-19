package com.example.taximuslim.data.repository.yandex

import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentRequest
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentResponse
import com.example.taximuslim.data.network.dto.yandex.cashbox.SentIdPayRequest
import io.reactivex.Single

interface IYandexRepository {


    /**
     * @param payment - object for creating payment
     * for getting 3d secure link
     *
     * @return single RXJava object with Yandex response
     */
    fun makePayment(payment : PaymentRequest): Single<PaymentResponse>

    /**
     * @param payId - pay id object
     *
     * @return single RXJava object with Yandex response
     */
    fun sentPayId(payId : SentIdPayRequest) : Single<PaymentResponse>
}