package com.example.taximuslim.dagger

import com.example.taximuslim.utils.location.IUserLocationProvider
import com.example.taximuslim.utils.location.UserLocationProvider
import dagger.Binds
import dagger.Module

@Module
interface LocationModule {

    @Binds
    fun bindMyLocationProvider(userLocationProvider: UserLocationProvider) :IUserLocationProvider

}