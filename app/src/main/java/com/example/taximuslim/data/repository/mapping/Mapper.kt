package com.example.taximuslim.data.repository.mapping

import com.example.taximuslim.data.network.dto.auth.NumberRegistrationStatusResponse
import com.example.taximuslim.domain.models.RegistrationStatus

class Mapper {
    companion object {
        fun mapRegistrationStatus(response: NumberRegistrationStatusResponse): RegistrationStatus =
            when (response.registrationStatusResponse) {
                "registration" -> RegistrationStatus.REGISTRATION
                "entry" -> RegistrationStatus.ENTRY
                else -> RegistrationStatus.ERROR
            }
        }

}