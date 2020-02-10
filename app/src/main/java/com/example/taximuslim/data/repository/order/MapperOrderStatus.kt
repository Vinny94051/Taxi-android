package com.example.taximuslim.data.repository.order

import com.example.taximuslim.data.network.dto.order.OrderStatusResponse
import com.example.taximuslim.data.repository.IMapper
import com.example.taximuslim.domain.order.models.DriverModel
import com.example.taximuslim.domain.order.models.StatusAndDrivers

class MapperOrderStatus : IMapper<OrderStatusResponse, StatusAndDrivers> {
    override fun mapFromEntity(data: OrderStatusResponse): StatusAndDrivers {
        return StatusAndDrivers(
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