package com.example.taximuslim.domain.interactors

import com.example.taximuslim.domain.models.driver.OrderHistoryModel

interface DriverInteractor {

    suspend fun fetchOrderHistory(): List<OrderHistoryModel>
}