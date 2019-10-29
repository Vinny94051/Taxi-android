package com.example.taximuslim.presenter.auth

import androidx.lifecycle.MutableLiveData
import com.example.taximuslim.baseUI.presenter.BasePresenter
import com.example.taximuslim.data.auth.NumberSaver
import com.example.taximuslim.data.auth.SmsCodeReceiver

class AuthPresenter : BasePresenter() {
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