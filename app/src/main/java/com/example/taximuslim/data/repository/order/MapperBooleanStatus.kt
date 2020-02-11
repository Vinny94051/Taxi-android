package com.example.taximuslim.data.repository.order

import com.example.taximuslim.data.network.dto.order.BooleanStatusResponse
import com.example.taximuslim.data.repository.IMapper
import com.example.taximuslim.domain.order.models.BooleanStatus

class MapperBooleanStatus : IMapper<BooleanStatusResponse, BooleanStatus> {
    override fun mapFromEntity(data: BooleanStatusResponse): BooleanStatus {
        return BooleanStatus(
            when (data.status) {
                "yes" -> true
                else -> false
            }
        )
    }
}