package com.example.taximuslim.domain.auth.driver

import com.example.taximuslim.data.network.remote.request.driver.*
import com.example.taximuslim.domain.models.driver.auth.CarMark
import com.example.taximuslim.domain.models.driver.auth.CarModel
import com.example.taximuslim.data.repository.auth.driver.DriverAuthRepository
import com.example.taximuslim.domain.models.driver.OrderHistoryModel
import com.example.taximuslim.domain.models.driver.auth.CarColor
import com.example.taximuslim.domain.models.driver.auth.DriverRule

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

    override suspend fun uploadDriverImage(request: UploadDriverImageRequest): Boolean {
        return repository.uploadDriverImage(request)
    }

    override suspend fun deleteDriverImage(request: DeleteDriverImageRequest): Boolean {
        return repository.deleteDriverImage(request)
    }

    override suspend fun fetchDriverRules(): List<DriverRule> {
        return repository.fetchDriverRules()
    }

    override suspend fun updateProfile(request: UpdateProfileRequest): Boolean {
        return repository.updateProfile(request)
    }

    override suspend fun updateDriverLicence(request: UpdateDriverLicenceRequest): Boolean {
        return repository.updateDriverLicence(request)
    }

    override suspend fun isNewDriver(): Boolean {
        return repository.isNewDriver()
    }

}