package com.example.taximuslim.data.repository.google

import com.example.taximuslim.App
import com.example.taximuslim.data.network.dto.google.directions.DirectionsResponse
import com.example.taximuslim.data.repository.IMapper
import com.example.taximuslim.domain.models.google.Route
import com.example.taximuslim.utils.mapfunc.IDecodePoly
import javax.inject.Inject

class MapperDirections : IMapper<DirectionsResponse, Route> {


    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var decodePoly : IDecodePoly


    override fun mapFromEntity(data: DirectionsResponse): Route =
        Route(data.routes.flatMap { _routes ->
            _routes.legs.map { legs ->
                legs.steps
            }
        }.flatMap { steps ->
            steps.flatMap {
                decodePoly.decodePoly(it.polyline.points)
            }
        })
}