package com.example.taximuslim.data.repository.auth.driver.carInfo

import android.content.Context
import com.example.taximuslim.App
import com.example.taximuslim.data.network.api.DriverApi
import com.example.taximuslim.data.network.remote.response.driver.CarMark
import com.example.taximuslim.data.network.remote.response.driver.CarModel
import com.example.taximuslim.domain.models.driver.auth.CarColor
import com.example.taximuslim.utils.prefference.getAuthHeader
import javax.inject.Inject

class CarInfoRepositoryImpl {

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
}