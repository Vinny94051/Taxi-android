package com.example.taximuslim.data.repository

import com.example.taximuslim.data.network.dto.auth.NumberRegistrationStatusResponse
import com.example.taximuslim.data.network.dto.auth.PreseptResponse
import com.example.taximuslim.domain.models.PreseptModel
import com.example.taximuslim.domain.models.RegistrationStatus

class Mapper {
    companion object {
        fun mapRegistrationStatus(response: NumberRegistrationStatusResponse): RegistrationStatus =
            when (response.registrationStatusResponse) {
                "registration" -> RegistrationStatus.REGISTRATION
                "entry" -> RegistrationStatus.ENTRY
                else -> RegistrationStatus.ERROR
            }

        fun mapPresept(response: PreseptResponse): PreseptModel =
            PreseptModel(response.backgroundImage, response.text)

        fun maToken(tokenResponse: Any): String {
            return tokenResponse.toString()
        }
    }

}