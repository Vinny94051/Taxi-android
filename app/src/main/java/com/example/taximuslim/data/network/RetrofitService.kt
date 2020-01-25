package com.example.taximuslim.data.network

import com.example.taximuslim.data.network.api.AuthApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
class RetrofitService {

    companion object {
        const val BASE_URL: String = "http://23.105.226.153:3333/"
    }

    private fun getRetrofit()=
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getAuthApi(): AuthApi = getRetrofit().create(AuthApi::class.java)


}