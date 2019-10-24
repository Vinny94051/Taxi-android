package com.example.taximuslim

import android.annotation.SuppressLint
import android.app.Application

class App : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: App? = null
        fun getApplicationContext() = instance!!.applicationContext
    }
}