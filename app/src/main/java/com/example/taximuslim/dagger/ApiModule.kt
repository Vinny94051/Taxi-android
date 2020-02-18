package com.example.taximuslim.dagger

import com.example.taximuslim.data.network.RetrofitService
import com.example.taximuslim.data.network.api.*
import com.example.taximuslim.data.repository.order.IOrderRepository
import dagger.Binds
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

    @JvmStatic
    @Singleton
    @Provides
    fun provideDriverApi(retrofit: RetrofitService) : DriverApi = retrofit.getDriverApi()

    @JvmStatic
    @Singleton
    @Provides
    fun provideYandexApi(retrofit: RetrofitService) : YandexApi = retrofit.getYandexApi()




}