package com.example.taximuslim.data.repository.order

import com.example.taximuslim.data.network.dto.order.OrderRequest
import com.example.taximuslim.data.repository.IMapper
import com.example.taximuslim.domain.order.models.OrderModel
import java.lang.NumberFormatException

class MapperOrder : IMapper<OrderRequest, OrderModel> {
    override fun mapFromEntity(data: OrderRequest): OrderModel =
        try {
            OrderModel(
                data.startPointAddress,
                data.startPointLat.toDouble(),
                data.startPointLng.toDouble(),
                data.endPointAddress,
                data.endPointLat.toDouble(),
                data.endPointLng.toDouble(),
                data.tariff,
                data.price,
                data.comment,
                data.paymentType
            )
        } catch (ex: NumberFormatException) {
            ex.printStackTrace()
            OrderModel(
                "",
                0.0,
                0.0,
                "",
                0.0,
                0.0,
                0,
                0,
                "",
                0
            )
        }

    fun mapToEntity(data: OrderModel): OrderRequest =
        OrderRequest(
            data.startPointAddress,
            data.startPointLat.toString(),
            data.startPointLng.toString(),
            data.endPointAddress,
            data.endPointLat.toString(),
            data.endPointLng.toString(),
            data.tariff,
            data.price,
            data.comment,
            data.paymentType
        )
}
