package com.example.taximuslim.presentation.view.auth.driver.politics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthDriverConfidentialityViewModel : ViewModel() {
    private val _navigateToNext = MutableLiveData<Boolean>(false)
    val navigateToNext: LiveData<Boolean>
        get() = _navigateToNext


    fun onMainButtonClick(){
            _navigateToNext.value = true
    }

    fun onNavigateToNext(){
        _navigateToNext.value = false
    }

    private val _navigateToPolitics = MutableLiveData<Boolean>()
    val navigateToPolitics: LiveData<Boolean>
        get() = _navigateToPolitics

    fun onPoliticsClick(){
        _navigateToPolitics.value = true
    }

    fun onNavigateToPolitics(){
        _navigateToPolitics.value = false
    }
}
