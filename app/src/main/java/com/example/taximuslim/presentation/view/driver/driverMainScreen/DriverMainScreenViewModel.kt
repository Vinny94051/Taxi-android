package com.example.taximuslim.presentation.view.driver.driverMainScreen

import androidx.lifecycle.*
import com.example.taximuslim.App
import com.example.taximuslim.domain.auth.driver.DriverAuthInteractor
import com.example.taximuslim.domain.interactors.DriverInteractor
import com.example.taximuslim.domain.models.driver.ProfileModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DriverMainScreenViewModel : ViewModel(), LifecycleObserver {
    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: DriverAuthInteractor

    @Inject
    lateinit var driverInteractor: DriverInteractor

    private val _isNewDriver = MutableLiveData<Boolean>(false)
    val isNewDriver: LiveData<Boolean>
        get() = _isNewDriver

    val profile = MutableLiveData<ProfileModel>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun createUser() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response= interactor.isNewDriver()
                _isNewDriver.value = response
                if (!response){
                    fetchProfile()
                }
            }catch (e: Exception){
                _isNewDriver.value = true
            }
        }
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun fetchProfile(){
        viewModelScope.launch(Dispatchers.Main) {
            try{
                val response = driverInteractor.fetchProfile()
                profile.value = response
            }catch (e: Exception){

            }
        }
    }
}