package com.example.taximuslim.presentation.view.auth.driver.carNumb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthDriverCarNumbViewModel : ViewModel() {
    private val _navigateToNext = MutableLiveData<Boolean>()
    val navigateToNext: LiveData<Boolean>
        get() = _navigateToNext

    fun onNavigateNext(){
        _navigateToNext.value = false
    }

    fun writeCarNumb(){
        _navigateToNext.value = true
        //TODO
    }
}
