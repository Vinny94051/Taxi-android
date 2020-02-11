package com.example.taximuslim.presentation.view.driver.profile

import androidx.lifecycle.*
import com.example.taximuslim.App
import com.example.taximuslim.domain.interactors.DriverInteractor
import com.example.taximuslim.domain.models.driver.ProfileModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class DriverProfileViewModel : ViewModel(), LifecycleObserver {
    init {
        App.appComponent.inject(this)
    }

    val profile = MutableLiveData<ProfileModel>()
    val balance = MutableLiveData<String>("0")

    @Inject
    lateinit var interactor: DriverInteractor

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun fetchProfile(){
        viewModelScope.launch(Dispatchers.Main) {
            try{
                val profileResponse = interactor.fetchProfile()
                profile.value = profileResponse

                val balanceResposne = interactor.fetchBalance()
                balance.value = balanceResposne.toString()
            }catch (e: Exception){
                throw e
            }
        }
    }

    fun onChangeProfileClick(){

    }

    fun onPayClick(){

    }
}
