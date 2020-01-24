package com.example.taximuslim.presentation.view.auth.driver.carPhoto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthDriverCarPhotoViewModel : ViewModel() {


    private val _navigateToNext = MutableLiveData<Boolean>()
    val navigateToNext: LiveData<Boolean>
        get() = _navigateToNext

    fun onNavigate(){
        _navigateToNext.value = false
    }

    fun checkDataAndGoNext(){
        //TODO(CHECKDATA)
        _navigateToNext.value = true
    }
}
