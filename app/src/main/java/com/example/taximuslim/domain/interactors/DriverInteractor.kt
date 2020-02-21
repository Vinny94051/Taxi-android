package com.example.taximuslim.domain.interactors

import com.example.taximuslim.data.network.dto.driver.DriverLocation
import com.example.taximuslim.data.network.dto.driver.FetchDriverStatusRequest
import com.example.taximuslim.data.network.dto.order.OrderRequest
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentRequest
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentResponse
import com.example.taximuslim.data.network.dto.yandex.cashbox.SentIdPayRequest
import com.example.taximuslim.data.network.remote.response.driver.DriverIncome
import com.example.taximuslim.domain.models.driver.ProfileModel
import com.example.taximuslim.domain.models.driver.OrderHistoryModel
import com.example.taximuslim.domain.models.driver.OrderToDriverModel
import io.reactivex.Observable
import io.reactivex.Single
import com.example.taximuslim.domain.models.driver.order.DriverOrderModel
import com.example.taximuslim.domain.order.models.StatusAndDrivers

interface DriverInteractor {

    suspend fun fetchOrderHistory(): List<OrderHistoryModel>

    suspend fun fetchDriverIncome(): DriverIncome

    suspend fun fetchProfile(): ProfileModel

    suspend fun fetchBalance(): Double

    suspend fun changePhone(phone: String): String

    suspend fun changeName(name: String): String

    suspend fun sendSmsCode(code: String): Boolean

    fun fetchTripList(driverLocation: DriverLocation): Observable<List<OrderToDriverModel>>

    fun fetchDriverStatus(body: FetchDriverStatusRequest) : Observable<StatusAndDrivers>
}