package com.example.taximuslim.domain.order

import com.example.taximuslim.data.network.dto.order.TariffRequest
import com.example.taximuslim.data.repository.google.GoogleRepo
import com.example.taximuslim.data.repository.order.OrderRepo
import com.example.taximuslim.domain.models.google.Route
import com.example.taximuslim.domain.order.models.TariffModel

class OrderInteractor : IOrderInteractor {

    private val orderRepo = OrderRepo()
    private val googleRepo = GoogleRepo()

    override fun getTariffies(
        token: String,
        location: TariffRequest,
        listener: ((TariffModel) -> Unit)
    ) {
        orderRepo.getTarrifs(token, location) { tarrifs ->
            listener.invoke(tarrifs)
        }
    }

    override fun getDirections(start: String, end: String, listener: ((Route) -> Unit)) {
        googleRepo.getDirections(start, end) { route ->
            listener.invoke(route)
        }
    }
}