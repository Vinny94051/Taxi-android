package com.example.taximuslim.domain.auth

import com.example.taximuslim.data.network.dto.auth.CheckSmsCodeRequest
import com.example.taximuslim.data.repository.auth.AuthRepo
import com.example.taximuslim.domain.auth.models.PreseptModel
import com.example.taximuslim.domain.auth.models.RegistrationStatus

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

    override fun getPresept(listener: (PreseptModel) -> Unit){
        authRepo.getPrecept{ presept ->
            listener.invoke(presept)
        }
    }

    override fun checkSmsCode(user : CheckSmsCodeRequest, listener : ((String) -> Unit)){
        authRepo.checkSmsCode(user){ token ->
            listener.invoke(token)
        }
    }

}