package com.example.taximuslim.data.network

import com.example.taximuslim.data.network.api.*
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
class RetrofitService {

    companion object {
        const val BASE_URL: String = "http://23.105.226.153:3333/"
        const val BASE_URL_GOOGLE_API = "https://maps.googleapis.com/maps/api/"
        const val BASE_URL_YANDEX_API = "https://payment.yandex.net/api/v3/"
    }

    private fun getRetrofit()=
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private fun getRetrofitForGoogleApi() =
        Retrofit.Builder()
            .baseUrl(BASE_URL_GOOGLE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun getRetrofitForYandexApi() =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun getAuthApi(): AuthApi = getRetrofit().create(AuthApi::class.java)

    fun getOrderApi() : OrderAPi = getRetrofit().create(OrderAPi::class.java)

    fun getDriverApi(): DriverApi = getRetrofit().create(DriverApi::class.java)

    fun getGoogleMapApi() : GoogleMapApi = getRetrofitForGoogleApi().create(GoogleMapApi::class.java)

    fun getGuideApi() : GuideApi = getRetrofit().create(GuideApi::class.java)

    fun getYandexApi() : YandexApi = getRetrofitForYandexApi().create(YandexApi::class.java)

}