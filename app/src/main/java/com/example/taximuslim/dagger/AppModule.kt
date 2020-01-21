package com.example.taximuslim.dagger

import android.app.Application
import android.content.Context
import com.example.taximuslim.data.network.RetrofitService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Singleton
    @Provides
    fun provideContext(): Context = app

    @Provides
    fun provideRetrofitService() = Retrofit.Builder()
        .baseUrl(RetrofitService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


}