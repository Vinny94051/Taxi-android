package com.example.taximuslim.presentation.view.auth.driver.aboutYou

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthDriverAboutYouViewModel : ViewModel() {

    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
    get() = _navigate

    fun writeDataAndGoNext(){
        //TODO
        _navigate.value = true
    }

    fun onNavigate(){
        _navigate.value = false
    }
}
