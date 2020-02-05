package com.example.taximuslim.data.repository.guide

import com.example.taximuslim.data.network.dto.guide.PlaceByLocationResponse
import com.example.taximuslim.data.repository.IMapper
import com.example.taximuslim.domain.models.guide.PlaceByLocationModel

class PlaceByLocationMapper : IMapper<List<PlaceByLocationResponse>, List<PlaceByLocationModel>> {
    override fun mapFromEntity(data: List<PlaceByLocationResponse>): List<PlaceByLocationModel> =
        data.map { responseItem ->
            PlaceByLocationModel(
                responseItem.placeId,
                responseItem.name,
                responseItem.text,
                responseItem.address,
                responseItem.latitude,
                responseItem.longitude,
                responseItem.imageUrl,
                responseItem.distance
            )
        }
}