package com.example.taximuslim.domain.interactors

import com.example.taximuslim.data.network.remote.response.driver.DriverIncome
import com.example.taximuslim.domain.models.driver.OrderHistoryModel

interface DriverInteractor {

    suspend fun fetchOrderHistory(): List<OrderHistoryModel>

    suspend fun fetchDriverIncome(): DriverIncome
}