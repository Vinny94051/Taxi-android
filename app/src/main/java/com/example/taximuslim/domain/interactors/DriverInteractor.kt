package com.example.taximuslim.domain.interactors

import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentRequest
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentResponse
import com.example.taximuslim.data.network.remote.response.driver.DriverIncome
import com.example.taximuslim.domain.models.driver.ProfileModel
import com.example.taximuslim.domain.models.driver.OrderHistoryModel
import io.reactivex.Single

interface DriverInteractor {

    suspend fun fetchOrderHistory(): List<OrderHistoryModel>

    suspend fun fetchDriverIncome(): DriverIncome

    suspend fun fetchProfile(): ProfileModel

    suspend fun fetchBalance(): Double

    suspend fun changePhone(phone: String): String

    suspend fun changeName(name: String): String

    suspend fun sendSmsCode(code: String): Boolean


    /**
     * @param payment - object for creating 3d secure link
     *
     * @return response which contains all fields which has PaymentRequest and 3d secure link
     * Single - RxJava object which you need to observe
     *
     * Function for getting 3ds link
     */
    fun makePayment(payment: PaymentRequest) : Single<PaymentResponse>
}