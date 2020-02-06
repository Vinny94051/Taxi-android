package com.example.taximuslim.presentation.view.driver.driverMainScreen

import androidx.lifecycle.*
import com.example.taximuslim.App
import com.example.taximuslim.domain.auth.driver.DriverAuthInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DriverMainScreenViewModel : ViewModel(), LifecycleObserver {
    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: DriverAuthInteractor

    private val _isNewDriver = MutableLiveData<Boolean>(false)
    val isNewDriver: LiveData<Boolean>
        get() = _isNewDriver

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun createUser() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                _isNewDriver.value = interactor.isNewDriver()
            }catch (e: Exception){
                _isNewDriver.value = true
            }
        }
    }
}