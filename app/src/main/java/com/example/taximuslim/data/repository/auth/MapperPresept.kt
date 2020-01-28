package com.example.taximuslim.data.repository.auth

import com.example.taximuslim.data.network.dto.auth.PreseptResponse
import com.example.taximuslim.data.repository.IMapper
import com.example.taximuslim.domain.auth.models.PreseptModel

class MapperPresept : IMapper<PreseptResponse, PreseptModel> {
    override fun mapFromEntity(data: PreseptResponse): PreseptModel =
        PreseptModel(data.backgroundImage, data.text)
}