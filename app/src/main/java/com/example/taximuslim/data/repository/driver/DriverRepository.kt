package com.example.taximuslim.data.repository.driver

import android.content.Context
import com.example.taximuslim.App
import com.example.taximuslim.data.network.api.DriverApi
import com.example.taximuslim.data.network.remote.response.driver.DriverIncome
import com.example.taximuslim.domain.models.driver.OrderHistoryModel
import com.example.taximuslim.utils.prefference.getAuthHeader
import javax.inject.Inject

class DriverRepository{
    init{
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var api : DriverApi

    @Inject
    lateinit var context : Context

    suspend fun fetchDriverHistory(): List<OrderHistoryModel>{
        val token = getAuthHeader(context)
        return api.fetchOrderHistoryList(token).map{
            OrderHistoryModel(
                it.auto, it.comment, it.date, it.distance, it.endAddress, it.startLat, it.startLgn,
                it.endLat, it.endLng, it.id, it.clientName, it.driverName, it.clientPhone,
                it.driverPhone, it.positionDriverLat, it.positionDriverLng, it.price,
                it.clientReply, it.request, it.startAddress, it.status, it.time, it.timeToGet
            )
        }
    }

    suspend fun fetchDriverIncome(): DriverIncome{
        val token = getAuthHeader(context)
        return api.fetchDriverIncome(token)
    }
}