package com.example.taximuslim.data.auth


object SmsCodeReceiver {
    /**
     * Stub
     * */
     fun load(listener: (Int) -> Unit) {
        val smsCode = 1111
        listener.invoke(smsCode)
    }

}