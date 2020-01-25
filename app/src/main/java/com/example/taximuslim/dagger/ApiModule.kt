package com.example.taximuslim.dagger

import com.example.taximuslim.data.network.RetrofitService
import com.example.taximuslim.data.network.api.AuthApi
import dagger.Binds
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
    fun provideRetrofitService() : RetrofitService = RetrofitService()

    @JvmStatic
    @Singleton
    @Provides
    fun provideAuthApi(retrofit: RetrofitService) : AuthApi = retrofit.getAuthApi()

}