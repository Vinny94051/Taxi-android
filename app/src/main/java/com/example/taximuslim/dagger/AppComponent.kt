package com.example.taximuslim.dagger

import com.example.taximuslim.data.repository.auth.AuthRepo
import com.example.taximuslim.data.repository.auth.driver.DriverAuthRepository
import com.example.taximuslim.data.repository.google.GoogleRepo
import com.example.taximuslim.data.repository.google.MapperDirections
import com.example.taximuslim.data.repository.guide.GuideRepo
import com.example.taximuslim.data.repository.order.OrderRepo
import com.example.taximuslim.domain.auth.IAuthInteractor
import com.example.taximuslim.domain.order.OrderInteractor
import com.example.taximuslim.presentation.view.auth.driver.aboutYou.AuthDriverAboutYouViewModel
import com.example.taximuslim.presentation.view.auth.driver.car.AuthDriverChooseCarViewModel
import com.example.taximuslim.presentation.view.auth.driver.carNumb.AuthDriverCarNumbViewModel
import com.example.taximuslim.presentation.view.auth.driver.carPhoto.AuthDriverCarPhotoViewModel
import com.example.taximuslim.presentation.view.auth.driver.documents.AuthDriverDocumentsViewModel
import com.example.taximuslim.presentation.view.auth.driver.rules.AuthDriverRuleViewModel
import com.example.taximuslim.presentation.view.auth.driver.validatePerson.AuthDriverValidatePersonViewModel
import com.example.taximuslim.presentation.view.auth.fragments.daughter.AuthorizationFragment
import com.example.taximuslim.presentation.view.auth.fragments.daughter.EnterSmsCodeFragment
import com.example.taximuslim.presentation.view.driver.driverMainScreen.DriverMainScreenViewModel
import com.example.taximuslim.presentation.view.mainscreen.FloatFragment
import com.example.taximuslim.presentation.view.mainscreen.TripProcessFragment
import com.example.taximuslim.presentation.viewmodel.auth.AuthViewModel
import com.example.taximuslim.presentation.viewmodel.maps.MainViewModel
import com.example.taximuslim.presentation.view.mainscreen.menu.fragments.guide.GuideViewModel
import com.example.taximuslim.presentation.view.mainscreen.menu.fragments.guide.PlacesListViewModel
import com.example.taximuslim.presentation.viewmodel.maps.TripViewModel
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
    fun inject(carInfoRepositoryImpl: DriverAuthRepository)
    fun inject(authDriverChooseCarViewModel: AuthDriverChooseCarViewModel)
    fun inject(authDriverCarNumbViewModel: AuthDriverCarNumbViewModel)
    fun inject(authDriverCarPhotoViewModel: AuthDriverCarPhotoViewModel)
    fun inject(authDriverAboutYouViewModel: AuthDriverAboutYouViewModel)
    fun inject(authDriverDocumentsViewModel: AuthDriverDocumentsViewModel)
    fun inject(authDriverValidatePersonViewModel: AuthDriverValidatePersonViewModel)
    fun inject(authDriverRuleViewModel: AuthDriverRuleViewModel)
    fun inject(driverMainScreenViewModelModel: DriverMainScreenViewModel)
    fun inject(mapperDirections: MapperDirections)
    fun inject(polyManager: PolyManager)
    fun inject(guideRepo: GuideRepo)
    fun inject(guideViewModel: GuideViewModel)
    fun inject(placesListViewModel: PlacesListViewModel)
    fun inject(orderInteractor: OrderInteractor)
    fun inject(tripProcessFragment: TripProcessFragment)
    fun inject(tripViewModel: TripViewModel)
}