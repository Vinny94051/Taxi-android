package com.example.taximuslim.data.repository.auth.driver

import android.content.Context
import com.example.taximuslim.App
import com.example.taximuslim.data.network.api.DriverApi
import com.example.taximuslim.data.network.remote.request.driver.DeleteDriverImageRequest
import com.example.taximuslim.data.network.remote.request.driver.DriverCarNumbRequest
import com.example.taximuslim.data.network.remote.request.driver.MarkModelColorRequest
import com.example.taximuslim.data.network.remote.request.driver.UploadDriverImageRequest
import com.example.taximuslim.domain.models.driver.auth.CarMark
import com.example.taximuslim.domain.models.driver.auth.CarModel
import com.example.taximuslim.domain.models.driver.auth.CarColor
import com.example.taximuslim.utils.prefference.getAuthHeader
import javax.inject.Inject

class DriverAuthRepository {

    init{
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var api : DriverApi

    @Inject
    lateinit var context : Context

    suspend fun fetchCarMarks(): List<CarMark>{
        val token = getAuthHeader(context)
        return  api.fetchCarMarks(token).map {
            CarMark(it.id, it.name)
        }
    }

    suspend fun fetchCarColors(): List<CarColor>{
        val token = getAuthHeader(context)
        return  api.fetchCarColors(token).map {
            CarColor(it.id, it.name)
        }
    }

    suspend fun fetchCarModels(carModelId: Int): List<CarModel>{
        val token = getAuthHeader(context)
        return  api.fetchCarModels(token, carModelId).map {
            CarModel(it.id, it.name, it.tariff)
        }
    }

    suspend fun sendCarNumb(carNumb: String): Boolean{
        val token = getAuthHeader(context)
        return (api.sendCarNumb(token, DriverCarNumbRequest(carNumb)).status == "yes")
    }

    suspend fun sendCarParams(params: MarkModelColorRequest): Boolean{
        val token = getAuthHeader(context)
        return (api.postMarkModelColor(token, params).status == "yes")
    }

    suspend fun uploadDriverImage(request: UploadDriverImageRequest): Boolean{
        val token = getAuthHeader(context)
        return (api.uploadDriverImage(token, request).status == "yed")
    }

    suspend fun deleteDriverImage(request: DeleteDriverImageRequest): Boolean{
        val token = getAuthHeader(context)
        return (api.deleteDriverImage(token, request).status == "yes")
    }
}