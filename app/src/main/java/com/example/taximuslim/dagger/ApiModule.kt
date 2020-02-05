package com.example.taximuslim.dagger

import com.example.taximuslim.data.network.RetrofitService
import com.example.taximuslim.data.network.api.AuthApi
import com.example.taximuslim.data.network.api.GoogleMapApi
import com.example.taximuslim.data.network.api.GuideApi
import com.example.taximuslim.data.network.api.OrderAPi
import dagger.Module
import dagger.Provides
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

    @JvmStatic
    @Singleton
    @Provides
    fun provideOrderApi(retrofit: RetrofitService) : OrderAPi = retrofit.getOrderApi()

    @JvmStatic
    @Singleton
    @Provides
    fun provideGoogleMapApi(retrofit: RetrofitService) : GoogleMapApi = retrofit.getGoogleMapApi()

    @JvmStatic
    @Singleton
    @Provides
    fun provideGuideApi(retrofit: RetrofitService) : GuideApi = retrofit.getGuideApi()


}