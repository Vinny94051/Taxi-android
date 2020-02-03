package com.example.taximuslim.domain.auth.driver

import com.example.taximuslim.data.network.remote.response.driver.CarMark
import com.example.taximuslim.data.network.remote.response.driver.CarModel
import com.example.taximuslim.domain.models.driver.auth.CarColor

interface CarInfoInteractor {

    suspend fun fetchCarColors(): List<CarColor>

    suspend fun fetchCarMarks(): List<CarMark>

    suspend fun fetchCarModels(markId: Int): List<CarModel>
}