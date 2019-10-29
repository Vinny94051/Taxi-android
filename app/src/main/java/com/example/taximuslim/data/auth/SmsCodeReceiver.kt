package com.example.taximuslim.data.auth

import com.example.taximuslim.baseUI.model.Loader

object SmsCodeReceiver : Loader<Int> {
    /**
     * Stub
     * */
    override fun load(listener: (Int) -> Unit) {
        val smsCode = 1111
        listener.invoke(smsCode)
    }

}