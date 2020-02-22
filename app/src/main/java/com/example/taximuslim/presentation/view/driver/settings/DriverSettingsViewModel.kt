package com.example.taximuslim.presentation.view.driver.settings

import android.util.Log
import androidx.lifecycle.*
import com.example.taximuslim.App
import com.example.taximuslim.domain.interactors.DriverInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class DriverSettingsViewModel : ViewModel(), LifecycleObserver {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: DriverInteractor


    val profileName = MutableLiveData<String>("")
    val profileNumb = MutableLiveData<String>("")

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun fetchProfile(){
        viewModelScope.launch(Dispatchers.Main) {
            try{
                val profile = interactor.fetchProfile()
                profileName.value = profile.driverName
                profileNumb.value = profile.phone
            }catch (e: Exception){
                Log.e("dsvm:", e.message.toString())
                e.printStackTrace()
            }
        }
    }

    fun changeName(name: String){
        viewModelScope.launch(Dispatchers.Main) {
            try{
               val newName = interactor.changeName(name)
                profileName.value = newName
            }catch (e: Exception){
                throw e
            }
        }
    }

    private val _changeNumbNavigate = MutableLiveData<Boolean>()
    val changeNumbNavigate: LiveData<Boolean>
        get() = _changeNumbNavigate

    fun onChangeNumbClick() {
        _changeNumbNavigate.value = true
    }

    fun onChangeNumbNavigate() {
        _changeNumbNavigate.value = false
    }

    private val _changeNameNavigate = MutableLiveData<Boolean>()
    val changeNameNavigate: LiveData<Boolean>
        get() = _changeNameNavigate

    fun onChangeNameClick() {
        _changeNameNavigate.value = true
    }

    fun onChangeNameNavigate() {
        _changeNameNavigate.value = false
    }

    fun onLogoutClick(){
        _changeNameNavigate.value = false
    }
}
