package com.example.taximuslim.presentation.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.App
import com.example.taximuslim.baseUI.viewmodel.NetworkViewModel
import com.example.taximuslim.data.auth.NumberSaver
import com.example.taximuslim.data.auth.SmsCodeReceiver
import com.example.taximuslim.domain.IAuthInteractor
import com.example.taximuslim.domain.models.RegistrationStatus
import javax.inject.Inject

class AuthViewModel : NetworkViewModel() {
    val liveDataSmsCode = MutableLiveData<Int>()
    private val numberSaver = NumberSaver()


    fun loadSmsCode() {
        SmsCodeReceiver.load { smsCode ->
            liveDataSmsCode.value = smsCode
        }
    }

    fun saveNumber(number: String) {
        numberSaver.save(number)
    }

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var interactor: IAuthInteractor

    private val _loadRegistrationNumberStatusLiveData = MutableLiveData<RegistrationStatus>()
    val loadRegistrationNumberStatus: LiveData<RegistrationStatus>
        get() = _loadRegistrationNumberStatusLiveData

    fun loadRegistrationNumberStatus(phoneNumber: String) {
            interactor.getNumberRegistrationStatus(phoneNumber) { status ->
                _loadRegistrationNumberStatusLiveData.value = status
            }
    }
}