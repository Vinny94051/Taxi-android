package com.example.taximuslim.data.repository.auth

import android.util.Log
import com.example.taximuslim.App
import com.example.taximuslim.data.network.api.AuthApi
import com.example.taximuslim.data.network.dto.NumberRequest
import com.example.taximuslim.data.network.dto.auth.CheckSmsCodeRequest
import com.example.taximuslim.data.network.dto.auth.NumberRegistrationStatusResponse
import com.example.taximuslim.data.network.dto.auth.PreseptResponse
import com.example.taximuslim.domain.auth.models.PreseptModel
import com.example.taximuslim.domain.auth.models.RegistrationStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AuthRepo {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var api: AuthApi

    fun getNumberRegistrationStatus(
        phoneNumber: String,
        listener: ((RegistrationStatus)) -> Unit
    ) {
        api.getNumberRegistrationStatus(NumberRequest(phoneNumber)).enqueue(object :
            Callback<NumberRegistrationStatusResponse> {
            override fun onFailure(call: Call<NumberRegistrationStatusResponse>, t: Throwable) {
                Log.e("repo::", t.message.toString())
            }

            override fun onResponse(
                call: Call<NumberRegistrationStatusResponse>,
                response: Response<NumberRegistrationStatusResponse>
            ) {
                if (response.isSuccessful)
                    response.body()?.let { _response ->
                        listener.invoke(
                            MapperRegistrationStatus().mapFromEntity(
                                _response
                            )
                        )
                    }
            }
        })
    }

    fun getPrecept(listener: (PreseptModel) -> Unit) {
        api.getPrecept().enqueue(object : Callback<PreseptResponse> {
            override fun onFailure(call: Call<PreseptResponse>, t: Throwable) {
                Log.e("repo::", t.message.toString())
            }

            override fun onResponse(
                call: Call<PreseptResponse>,
                response: Response<PreseptResponse>
            ) {
                if (response.isSuccessful)
                    response.body()?.let { _response ->
                        listener.invoke(
                            MapperPresept().mapFromEntity(
                                _response
                            )
                        )
                    }
            }
        })
    }

    fun checkSmsCode(user: CheckSmsCodeRequest, listener: (String) -> Unit) {
        api.checkSmsCode(user).enqueue(object : Callback<Any> {
            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.e("repo::", t.message.toString())
            }

            override fun onResponse(
                call: Call<Any>,
                response: Response<Any>
            ) {
                if (response.isSuccessful)
                    response.body()?.let { _response ->
                        listener.invoke(
                            MapperToken().mapFromEntity(
                                _response
                            )
                        )
                    }
            }
        })
    }

}