package com.example.taximuslim.dagger

import com.example.taximuslim.data.network.RetrofitService
import com.example.taximuslim.data.network.api.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ApiModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofitService() : Retrofit = Retrofit.Builder()
        .baseUrl(RetrofitService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @JvmStatic
    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit) : AuthApi =
        retrofit.create(AuthApi::class.java)
}