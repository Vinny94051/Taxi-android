package com.example.taximuslim.data.repository.driver

import com.example.taximuslim.data.network.remote.response.driver.OrderResponse
import com.example.taximuslim.data.repository.IMapper
import com.example.taximuslim.domain.models.driver.OrderToDriverModel

class MapperOrderRequest : IMapper<List<OrderResponse>?, List<OrderToDriverModel>> {
    override fun mapFromEntity(data: List<OrderResponse>?): List<OrderToDriverModel> =
        data?.map { item ->
            OrderToDriverModel(
                item.id,
                item.clientName,
                item.startAddress,
                item.startLat,
                item.startLgn,
                item.endAddress,
                item.endLat,
                item.endLgn,
                item.price,
                item.comment,
                item.distance,
                item.date,
                item.time
            )
        } ?: mutableListOf<OrderToDriverModel>(
            OrderToDriverModel(
                0,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
            )
        )


}