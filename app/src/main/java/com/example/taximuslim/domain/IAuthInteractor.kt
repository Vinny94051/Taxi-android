package com.example.taximuslim.domain

import com.example.taximuslim.domain.models.RegistrationStatus

interface IAuthInteractor {
     fun getNumberRegistrationStatus(
        phoneNumber: String,
        listener: ((RegistrationStatus) -> Unit)
    )
}