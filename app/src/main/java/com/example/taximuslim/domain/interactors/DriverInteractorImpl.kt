package com.example.taximuslim.domain.interactors

import com.example.taximuslim.data.network.remote.response.driver.DriverIncome
import com.example.taximuslim.data.repository.driver.DriverRepository
import com.example.taximuslim.domain.models.driver.ProfileModel
import com.example.taximuslim.domain.models.driver.OrderHistoryModel

class DriverInteractorImpl : DriverInteractor {

    private val repository =
        DriverRepository()

    override suspend fun fetchOrderHistory(): List<OrderHistoryModel> {
        return repository.fetchDriverHistory()
    }

    override suspend fun fetchDriverIncome(): DriverIncome {
        return repository.fetchDriverIncome()
    }

    override suspend fun fetchProfile(): ProfileModel {
        return repository.fetchProfile()
    }

    override suspend fun fetchBalance(): Double {
        return repository.fetchBalance()
    }

    override suspend fun changePhone(phone: String): String {
        return repository.changePhone(phone)
    }

    override suspend fun changeName(name: String): String {
       return repository.changeName(name)
    }

    override suspend fun sendSmsCode(code: String): Boolean {
        return repository.sendSmsCode(code)
    }
}