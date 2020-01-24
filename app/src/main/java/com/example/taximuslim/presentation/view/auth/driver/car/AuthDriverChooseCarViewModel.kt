package com.example.taximuslim.presentation.view.auth.driver.car

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthDriverChooseCarViewModel : ViewModel() {

    private val _navigateToNext = MutableLiveData<Boolean>()
    val navigateTonext: LiveData<Boolean>
    get() = _navigateToNext

    fun onNavigate(){
        _navigateToNext.value = false
    }

    fun checkAndWriteData(){
        //TODO
        _navigateToNext.value = true
    }
}
