package com.example.taximuslim.domain.order

import com.example.taximuslim.data.network.dto.order.TrafficRequest
import com.example.taximuslim.data.repository.order.OrderRepo
import com.example.taximuslim.domain.order.models.TariffModel

class OrderInteractor : IOrderInteractor {

    private val orderRepo = OrderRepo()

    override fun getTariffies(
        token: String,
        location: TrafficRequest,
        listener: ((TariffModel) -> Unit)
    ) {
        orderRepo.getTarrifs(token, location) { tarrifs ->
            listener.invoke(tarrifs)
        }
    }
}