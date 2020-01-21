package com.example.taximuslim.presentation.viewmodel.auth

import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.baseUI.viewmodel.BaseViewModel
import com.example.taximuslim.data.auth.NumberSaver
import com.example.taximuslim.data.auth.SmsCodeReceiver

class AuthViewModel : BaseViewModel() {
    val liveDataSmsCode = MutableLiveData<Int>()
    private val numberSaver = NumberSaver()


    fun loadSmsCode(){
        SmsCodeReceiver.load { smsCode ->
            liveDataSmsCode.value = smsCode
        }
    }

    fun saveNumber(number : String){
        numberSaver.save(number)
    }
}