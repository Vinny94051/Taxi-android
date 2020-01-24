package com.example.taximuslim.presentation.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.App
import com.example.taximuslim.baseUI.viewmodel.NetworkViewModel
import com.example.taximuslim.data.network.dto.auth.CheckSmsCodeRequest
import com.example.taximuslim.domain.IAuthInteractor
import com.example.taximuslim.domain.models.PreseptModel
import com.example.taximuslim.domain.models.RegistrationStatus
import javax.inject.Inject

class AuthViewModel : NetworkViewModel() {

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

    private val _loadPreseptLiveData = MutableLiveData<PreseptModel>()
    val loadPreseptLiveData: LiveData<PreseptModel>
        get() = _loadPreseptLiveData

    fun loadPresept() {
        interactor.getPresept { presept ->
            _loadPreseptLiveData.value = presept
        }
    }


    private val _loadTokenLiveData = MutableLiveData<String>()
    val loadTokenLiveData: LiveData<String>
        get() = _loadTokenLiveData

    fun loadToken(user : CheckSmsCodeRequest){
        interactor.checkSmsCode(user){ token ->
            _loadTokenLiveData.value = token
        }
    }
}