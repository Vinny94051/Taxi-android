package com.example.taximuslim.data.repository.auth

import com.example.taximuslim.data.network.dto.auth.NumberRegistrationStatusResponse
import com.example.taximuslim.data.repository.IMapper
import com.example.taximuslim.domain.auth.models.RegistrationStatus

class MapperRegistrationStatus : IMapper<NumberRegistrationStatusResponse, RegistrationStatus> {
    override fun mapFromEntity(data: NumberRegistrationStatusResponse): RegistrationStatus =
        when (data.registrationStatusResponse) {
            "registration" -> RegistrationStatus.REGISTRATION
            "entry" -> RegistrationStatus.ENTRY
            else -> RegistrationStatus.ERROR
        }
}