package com.example.taximuslim.domain.auth.driver

import com.example.taximuslim.data.network.remote.response.driver.CarMark
import com.example.taximuslim.data.network.remote.response.driver.CarModel
import com.example.taximuslim.data.repository.auth.driver.carInfo.CarInfoRepositoryImpl
import com.example.taximuslim.domain.models.driver.auth.CarColor

class CarInfoInteractorImpl : CarInfoInteractor {

    private val repository =  CarInfoRepositoryImpl()

    override suspend fun fetchCarColors(): List<CarColor> {
       return repository.fetchCarColors()
    }

    override suspend fun fetchCarMarks(): List<CarMark> {
       return repository.fetchCarMarks()
    }

    override suspend fun fetchCarModels(markId: Int): List<CarModel> {
        return repository.fetchCarModels(markId)
    }
}