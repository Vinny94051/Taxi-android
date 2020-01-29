package com.example.taximuslim.presentation.view.driver.settings.changeNumber

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChangeNumbViewModel : ViewModel() {
    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate

    fun onNavigate(){
        _navigate.value = true
    }

    fun onMainButtonClick(){
        //TODO(затычка)
        _navigate.value = true
    }

}
