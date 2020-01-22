package com.example.taximuslim.domain

import com.example.taximuslim.App
import com.example.taximuslim.data.repository.AuthRepo
import com.example.taximuslim.domain.models.RegistrationStatus
import javax.inject.Inject

class AuthInteractor : IAuthInteractor {

    private val authRepo = AuthRepo()

    override fun getNumberRegistrationStatus(
        phoneNumber: String,
        listener: ((RegistrationStatus) -> Unit)
    ) {
        authRepo.getNumberRegistrationStatus(phoneNumber) { registrationStatus ->
            listener.invoke(registrationStatus)
        }
    }

}