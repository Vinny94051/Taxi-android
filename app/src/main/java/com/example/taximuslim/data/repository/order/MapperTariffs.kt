package com.example.taximuslim.data.repository.order

import com.example.taximuslim.data.network.dto.order.TariffsResponse
import com.example.taximuslim.data.repository.IMapper
import com.example.taximuslim.domain.order.models.TariffModel

class MapperTariffs : IMapper<TariffsResponse, TariffModel> {
    override fun mapFromEntity(data: TariffsResponse): TariffModel =
        TariffModel(
            data.economy,
            data.comfort,
            data.business
        )
}