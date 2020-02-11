package com.example.taximuslim.data.repository.order

import com.example.taximuslim.data.network.dto.order.OrderStatusResponse
import com.example.taximuslim.data.repository.IMapper
import com.example.taximuslim.domain.order.models.DriverModel
import com.example.taximuslim.domain.order.models.StatusAndDrivers

class MapperOrderStatus : IMapper<OrderStatusResponse, StatusAndDrivers> {
    override fun mapFromEntity(data: OrderStatusResponse): StatusAndDrivers {
        return StatusAndDrivers(
            data.id,
            data.clientPhone,
            data.driverPhone,
            data.clientName,
            data.driverName,
            data.startPointAddress,
            data.startPointLatitude,
            data.startPointLongitude,
            data.endPointAddress,
            data.endPointLatitude,
            data.endPointLongitude,
            data.driverPositionLatitude ?: 0.0,
            data.driverPositionLongitude ?: 0.0,
            data.price,
            data.comment,
            data.distance,
            data.time,
            data.timeToGet ?: "",
            data.car,
            data.clientReply,
            data.date,
            data.status,
            data.driversList.map { driver ->
                DriverModel(
                    driver.id,
                    driver.name,
                    driver.car
                )
            }
        )
    }
}