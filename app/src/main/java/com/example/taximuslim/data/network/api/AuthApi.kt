package com.example.taximuslim.data.network.api

import com.example.taximuslim.data.network.dto.auth.CheckSmsCodeRequest
import com.example.taximuslim.data.network.dto.auth.CheckSmsCodeResponse
import com.example.taximuslim.data.network.dto.auth.NumberRegistrationStatusResponse
import com.example.taximuslim.data.network.dto.auth.PreseptResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("client/signup")
    suspend fun getNumberRegistrationStatus(
        phoneNumber : String
    ) : NumberRegistrationStatusResponse

    @POST("client/login")
    suspend fun checkSmsCode(
        phoneAndCode : CheckSmsCodeRequest
    ) : CheckSmsCodeResponse

    @GET("precept")
    suspend fun getPrecept() : PreseptResponse
}