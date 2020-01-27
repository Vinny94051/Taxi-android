package com.example.taximuslim.dagger

import android.app.Application
import android.content.Context
import com.example.taximuslim.data.repository.auth.AuthRepo
import com.example.taximuslim.domain.auth.AuthInteractor
import com.example.taximuslim.domain.auth.IAuthInteractor
import com.example.taximuslim.domain.order.IOrderInteractor
import com.example.taximuslim.domain.order.OrderInteractor
import com.example.taximuslim.presentation.viewmodel.auth.AuthViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        ApiModule::class,
        LocationModule::class
    ]
)
class AppModule(private val app: Application) {

    @Singleton
    @Provides
    fun provideContext(): Context = app

    @Provides
    fun provideAuthRepo(): AuthRepo =
        AuthRepo()

    @Provides
    fun provideAuthInteractor(): IAuthInteractor =
        AuthInteractor()

    @Provides
    fun provideAuthViewModel(): AuthViewModel = AuthViewModel()

    @Provides
    fun provideOrderInteractor() : IOrderInteractor = OrderInteractor()
}