package com.example.taximuslim.presentation.view.auth.driver.request

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthDriverRequestViewModel : ViewModel() {
    private val _mainNavigation = MutableLiveData<Boolean>()
    val mainNavigation: LiveData<Boolean>
    get() = _mainNavigation

    fun onMainButtonClick(){
        _mainNavigation.value = true
    }

    fun onMainNavigate(){
        _mainNavigation.value = false
    }
}
