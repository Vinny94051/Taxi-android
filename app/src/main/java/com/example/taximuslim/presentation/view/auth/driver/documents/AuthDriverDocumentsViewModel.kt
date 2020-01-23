package com.example.taximuslim.presentation.view.auth.driver.documents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthDriverDocumentsViewModel : ViewModel() {

    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
    get() = _navigate

    fun onNavigate(){
        _navigate.value = false
    }

    fun checkAndWriteData(){
        //TODO
        _navigate.value = true
    }
}
