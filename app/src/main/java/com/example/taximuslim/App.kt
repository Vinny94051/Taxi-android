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
        const val API_KEY = "AIzaSyDcrOph1sg1RemEd-XkUavOdr1O5uf6WLg"
        const val API_KEY_DIRECTIONS = "AIzaSyCOsDzwdBQCwZjJ9keUKeBYdUBydijVSoI"
    }

    private fun initDagger(app: Application): AppComponent {
        return DaggerAppComponent.builder().appModule(AppModule(app))
            .apiModule(ApiModule)
            .build()

    }
}