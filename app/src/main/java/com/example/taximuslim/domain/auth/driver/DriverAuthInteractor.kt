package com.example.taximuslim.domain.auth.driver

import com.example.taximuslim.data.network.remote.request.driver.DeleteDriverImageRequest
import com.example.taximuslim.data.network.remote.request.driver.MarkModelColorRequest
import com.example.taximuslim.data.network.remote.request.driver.UploadDriverImageRequest
import com.example.taximuslim.domain.models.driver.auth.CarMark
import com.example.taximuslim.domain.models.driver.auth.CarModel
import com.example.taximuslim.domain.models.driver.auth.CarColor

interface DriverAuthInteractor {

    suspend fun fetchCarColors(): List<CarColor>

    suspend fun fetchCarMarks(): List<CarMark>

    suspend fun fetchCarModels(markId: Int): List<CarModel>

    suspend fun sendCarNumb(carNumb: String): Boolean

    suspend fun sendCarParams(params: MarkModelColorRequest): Boolean

    suspend fun uploadDriverImage(request: UploadDriverImageRequest): Boolean

    suspend fun deleteDriverImage(request: DeleteDriverImageRequest): Boolean
}