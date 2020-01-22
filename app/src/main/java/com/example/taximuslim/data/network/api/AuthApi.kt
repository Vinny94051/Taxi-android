package com.example.taximuslim.data.network.api

import com.example.taximuslim.data.network.dto.NumberRequest
import com.example.taximuslim.data.network.dto.auth.CheckSmsCodeRequest
import com.example.taximuslim.data.network.dto.auth.CheckSmsCodeResponse
import com.example.taximuslim.data.network.dto.auth.NumberRegistrationStatusResponse
import com.example.taximuslim.data.network.dto.auth.PreseptResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Inject

interface AuthApi {

    @POST("client/signup")
    fun getNumberRegistrationStatus(
       @Body phone : NumberRequest
    ) : Call<NumberRegistrationStatusResponse>

    @POST("client/login")
    suspend fun checkSmsCode(
        phoneAndCode : CheckSmsCodeRequest
    ) : CheckSmsCodeResponse

    @GET("precept")
    suspend fun getPrecept() : PreseptResponse
}