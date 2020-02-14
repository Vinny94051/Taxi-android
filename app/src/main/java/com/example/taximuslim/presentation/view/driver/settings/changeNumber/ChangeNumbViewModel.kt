package com.example.taximuslim.presentation.view.driver.settings.changeNumber

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taximuslim.App
import com.example.taximuslim.domain.interactors.DriverInteractor
import com.example.taximuslim.presentation.view.auth.driver.LoadingStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChangeNumbViewModel : ViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: DriverInteractor

    val phoneNumb = MutableLiveData<String>("")
    val phoneStatus = MutableLiveData<LoadingStatus>(LoadingStatus.NULL)
    val existError = MutableLiveData<Int>(  View.GONE)

    fun focused() {
        if (phoneStatus.value == LoadingStatus.NULL){
            phoneStatus.value = LoadingStatus.COMPLETE
        }
    }

    fun onChange(){
        phoneStatus.value = LoadingStatus.COMPLETE
        existError.value = View.GONE
    }


    fun unfocused() {
        if (phoneStatus.value == LoadingStatus.COMPLETE) {
            phoneStatus.value = LoadingStatus.NULL
        }
    }

    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate

    fun onNavigate() {
        _navigate.value = false
    }

    fun onMainButtonClick() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                if (phoneNumb.value!!.length == 10) {
                    val response = interactor.changePhone(phoneNumb.value!!)
                    when (response) {
                        "no_phone" -> {
                            existError.value = View.VISIBLE
                        }
                        "yes" -> {
                            _navigate.value = true
                        }
                    }
                } else {
                    phoneStatus.value = LoadingStatus.ERROR
                }

            } catch (e: Exception) {
                phoneStatus.value = LoadingStatus.ERROR
            }
        }

    }

}
