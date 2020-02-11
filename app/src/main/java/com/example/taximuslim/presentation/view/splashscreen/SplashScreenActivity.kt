package com.example.taximuslim.presentation.view.splashscreen

import android.content.Intent
import android.os.Bundle
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.activivty.BaseActivity
import com.example.taximuslim.presentation.view.auth.AuthActivity
import com.example.taximuslim.presentation.view.splashscreen.fragments.SplashScreenFragment
import com.example.taximuslim.utils.prefference.getAuthHeader

class SplashScreenActivity : BaseActivity() {

    override fun layoutId() = R.layout.controller_splash_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getAuthHeader(this) != "")
            addFragment(
                SplashScreenFragment.INSTANCE,
                R.id.container_splash_screen,
                SplashScreenFragment.FRAGMENT_ID
            )
        else {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }
}