package com.example.taximuslim.domain.auth

import com.example.taximuslim.data.network.dto.auth.CheckSmsCodeRequest
import com.example.taximuslim.domain.auth.models.PreseptModel
import com.example.taximuslim.domain.auth.models.RegistrationStatus

interface IAuthInteractor {
    fun getNumberRegistrationStatus(
        phoneNumber: String,
        listener: ((RegistrationStatus) -> Unit)
    )

    fun getPresept(listener: ((PreseptModel) -> Unit))

    fun checkSmsCode(user: CheckSmsCodeRequest, listener: ((String) -> Unit))
}