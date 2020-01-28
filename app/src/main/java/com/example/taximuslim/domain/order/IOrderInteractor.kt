package com.example.taximuslim.domain.order

import com.example.taximuslim.data.network.dto.order.TariffRequest
import com.example.taximuslim.domain.order.models.TariffModel

interface IOrderInteractor {
    fun getTariffies(token: String, location: TariffRequest, listener: ((TariffModel) -> Unit))
}