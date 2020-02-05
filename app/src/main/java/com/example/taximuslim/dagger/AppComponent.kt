package com.example.taximuslim.dagger

import com.example.taximuslim.data.repository.auth.AuthRepo
import com.example.taximuslim.data.repository.google.GoogleRepo
import com.example.taximuslim.data.repository.google.MapperDirections
import com.example.taximuslim.data.repository.guide.GuideRepo
import com.example.taximuslim.data.repository.order.OrderRepo
import com.example.taximuslim.domain.auth.IAuthInteractor
import com.example.taximuslim.presentation.view.auth.fragments.daughter.AuthorizationFragment
import com.example.taximuslim.presentation.view.auth.fragments.daughter.EnterSmsCodeFragment
import com.example.taximuslim.presentation.view.clientorder.FloatFragment
import com.example.taximuslim.presentation.viewmodel.auth.AuthViewModel
import com.example.taximuslim.presentation.viewmodel.maps.MainViewModel
import com.example.taximuslim.utils.mapfunc.DistanceCalculator
import com.example.taximuslim.utils.mapfunc.PolyManager
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    fun inject(target: AuthRepo)
    fun inject(target: IAuthInteractor)
    fun inject(target: AuthViewModel)
    fun inject(target: AuthorizationFragment)
    fun inject(enterSmsCodeFragment: EnterSmsCodeFragment)
    fun inject(mainViewModel: MainViewModel)
    fun inject(orderRepo : OrderRepo)
    fun inject(distanceCalculator: DistanceCalculator)
    fun inject(floatFragment: FloatFragment)
    fun inject(googleRepo: GoogleRepo)
    fun inject(mapperDirections: MapperDirections)
    fun inject(polyManager: PolyManager)
    fun inject(guideRepo: GuideRepo)

}