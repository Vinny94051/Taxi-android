package com.example.taximuslim

import android.app.Application
import android.content.Context
import com.example.taximuslim.dagger.ApiModule
import com.example.taximuslim.dagger.AppComponent
import com.example.taximuslim.dagger.AppModule
import com.example.taximuslim.dagger.DaggerAppComponent

class App : Application() {

    init {
        instance = this
        appComponent = initDagger(this)
    }

    companion object {
        private var instance: App? = null
        lateinit var appComponent: AppComponent
        fun getApplicationContext(): Context = instance!!.applicationContext
    }

    private fun initDagger(app: Application): AppComponent {
        return DaggerAppComponent.builder().appModule(AppModule(app))
            .apiModule(ApiModule)
            .build()

    }
}