package com.example.taximuslim.dagger

import android.app.Application
import android.content.Context
import com.example.taximuslim.data.network.dto.Token
import com.example.taximuslim.data.repository.auth.AuthRepo
import com.example.taximuslim.domain.auth.AuthInteractor
import com.example.taximuslim.domain.auth.IAuthInteractor
import com.example.taximuslim.domain.auth.driver.DriverAuthInteractor
import com.example.taximuslim.domain.auth.driver.DriverAuthInteractorImpl
import com.example.taximuslim.domain.interactors.DriverInteractor
import com.example.taximuslim.domain.interactors.DriverInteractorImpl
import com.example.taximuslim.domain.order.IOrderInteractor
import com.example.taximuslim.domain.order.OrderInteractor
import com.example.taximuslim.presentation.viewmodel.auth.AuthViewModel
import com.example.taximuslim.utils.mapfunc.DecodePoly
import com.example.taximuslim.utils.mapfunc.MapManager
import com.example.taximuslim.utils.mapfunc.IDecodePoly
import com.example.taximuslim.utils.prefference.getAuthHeader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        ApiModule::class,
        LocationModule::class,
        DataModule::class
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
    fun provideOrderInteractor(): IOrderInteractor = OrderInteractor()

    @Provides
    fun providesFetchAddressIntentService(): MapManager =
        MapManager(app)

    @Provides
    fun providesDecodePoly(): IDecodePoly = DecodePoly()

    @Provides
    fun provideToken(): Token = Token(getAuthHeader(app))


    @Provides
    fun provideDriverInfoInteractor(): DriverAuthInteractor =
        DriverAuthInteractorImpl()

    @Provides
    fun provideDriverInteractor(): DriverInteractor =
        DriverInteractorImpl()

}