package com.example.taximuslim.domain.auth.driver

import com.example.taximuslim.data.network.remote.request.driver.MarkModelColorRequest
import com.example.taximuslim.data.network.remote.response.driver.CarMark
import com.example.taximuslim.data.network.remote.response.driver.CarModel
import com.example.taximuslim.data.repository.auth.driver.DriverAuthRepository
import com.example.taximuslim.domain.models.driver.auth.CarColor

class DriverAuthInteractorImpl : DriverAuthInteractor {

    private val repository =
        DriverAuthRepository()

    override suspend fun fetchCarColors(): List<CarColor> {
       return repository.fetchCarColors()
    }

    override suspend fun fetchCarMarks(): List<CarMark> {
       return repository.fetchCarMarks()
    }

    override suspend fun fetchCarModels(markId: Int): List<CarModel> {
        return repository.fetchCarModels(markId)
    }


    override suspend fun sendCarNumb(carNumb: String): Boolean{
        return repository.sendCarNumb(carNumb)
    }

    override suspend fun sendCarParams(params: MarkModelColorRequest): Boolean {
        return repository.sendCarParams(params)
    }
}