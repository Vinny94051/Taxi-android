package com.example.taximuslim.domain.yandex

import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentRequest
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentResponse
import com.example.taximuslim.data.network.dto.yandex.cashbox.SentIdPayRequest
import io.reactivex.Single

interface IYandexInteractor {
    /**
     * @param payment - object for creating 3d secure link
     *
     * @return response which contains all fields which has PaymentRequest and 3d secure link
     *
     * Function for getting 3ds link
     */
    fun makePayment(payment: PaymentRequest) : Single<PaymentResponse>

    /**
     * @param payId - object which contains payment id
     * You can get it from makePayment response
     *
     * @return response which contains all fields which has PaymentRequest and 3d secure link
     */
    fun sentPayId(payId : SentIdPayRequest) : Single<PaymentResponse>
}