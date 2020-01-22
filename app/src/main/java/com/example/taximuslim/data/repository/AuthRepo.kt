package com.example.taximuslim.data.repository

import android.util.Log
import com.example.taximuslim.App
import com.example.taximuslim.data.network.api.AuthApi
import com.example.taximuslim.data.network.dto.NumberRequest
import com.example.taximuslim.data.network.dto.auth.NumberRegistrationStatusResponse
import com.example.taximuslim.data.repository.mapping.Mapper
import com.example.taximuslim.domain.models.RegistrationStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AuthRepo @Inject constructor() {

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
                if(response.isSuccessful)
                    response.body()?.let{ reponse ->
                        listener.invoke(Mapper.mapRegistrationStatus(reponse))
                    }
          }
      })
    }

}