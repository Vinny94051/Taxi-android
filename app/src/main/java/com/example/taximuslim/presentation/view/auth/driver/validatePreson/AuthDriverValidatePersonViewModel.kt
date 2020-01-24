package com.example.taximuslim.presentation.view.auth.driver.validatePreson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthDriverValidatePersonViewModel : ViewModel() {
    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate

    fun onMainButtonClick(){
        //todo
        _navigate.value = true
    }

    fun onNavigate(){
        _navigate.value = false
    }
}
