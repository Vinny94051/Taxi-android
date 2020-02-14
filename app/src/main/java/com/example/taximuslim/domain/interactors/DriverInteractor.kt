package com.example.taximuslim.domain.interactors

import com.example.taximuslim.data.network.remote.request.driver.OrderListRequest
import com.example.taximuslim.data.network.remote.response.driver.DriverIncome
import com.example.taximuslim.domain.models.driver.ProfileModel
import com.example.taximuslim.domain.models.driver.OrderHistoryModel
import com.example.taximuslim.domain.models.driver.order.DriverOrderModel

interface DriverInteractor {

    suspend fun fetchOrderHistory(): List<OrderHistoryModel>

    suspend fun fetchDriverIncome(): DriverIncome

    suspend fun fetchProfile(): ProfileModel

    suspend fun fetchBalance(): Double

    suspend fun changePhone(phone: String): String

    suspend fun changeName(name: String): String

    suspend fun sendSmsCode(code: String): Boolean

    suspend fun fetchOrderList(request: OrderListRequest): List<DriverOrderModel>
}