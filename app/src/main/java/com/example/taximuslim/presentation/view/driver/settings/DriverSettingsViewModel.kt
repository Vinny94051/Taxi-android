package com.example.taximuslim.presentation.view.driver.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DriverSettingsViewModel : ViewModel() {
    val profileName = MutableLiveData<String>("Иржан")
    val profileNumb = MutableLiveData<String>("+88805553535")

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
