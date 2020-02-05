package com.example.taximuslim.data.repository.guide

import com.example.taximuslim.data.network.dto.guide.PlaceByLocationRequest
import com.example.taximuslim.data.repository.IMapper
import com.example.taximuslim.domain.models.guide.UserPlaceByLocationModel

class UserPlaceByLocationMapper : IMapper<UserPlaceByLocationModel, PlaceByLocationRequest> {
    override fun mapFromEntity(data: UserPlaceByLocationModel): PlaceByLocationRequest =
        PlaceByLocationRequest(data.id, data.latitude, data.longitude)
}