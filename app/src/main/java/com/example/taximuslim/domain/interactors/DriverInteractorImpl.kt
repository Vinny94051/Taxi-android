package com.example.taximuslim.domain.interactors

import com.example.taximuslim.data.repository.driver.DriverRepository
import com.example.taximuslim.domain.models.driver.OrderHistoryModel

class DriverInteractorImpl : DriverInteractor {

    private val repository =
        DriverRepository()

    override suspend fun fetchOrderHistory(): List<OrderHistoryModel> {
        return repository.fetchDriverHistory()
    }
}