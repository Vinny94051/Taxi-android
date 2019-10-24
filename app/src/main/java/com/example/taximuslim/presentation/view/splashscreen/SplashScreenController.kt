package com.example.taximuslim.presentation.view.splashscreen

import android.os.Bundle
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.controller.BaseController
import com.example.taximuslim.presentation.view.splashscreen.fragments.SplashScreenFragment

class SplashScreenController : BaseController() {

    override fun layoutId() = R.layout.controller_splash_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(
            SplashScreenFragment.INSTANCE,
            R.id.container_splash_screen,
            SplashScreenFragment.FRAGMENT_ID
        )
    }
}