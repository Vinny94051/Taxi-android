package com.example.taximuslim.presentation.view.auth.driver.politics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthDriverConfidentialityViewModel : ViewModel() {
    var politicsSelected = MutableLiveData<Boolean>().also{ it.value = false }
    fun onCheckBoxClick(){
        politicsSelected.value = !(politicsSelected.value ?: false)
    }

    private val _navigateToNext = MutableLiveData<Boolean>()
    val navigateToNext: LiveData<Boolean>
        get() = _navigateToNext

    fun onMainButtonClick(){
        //TODO
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
