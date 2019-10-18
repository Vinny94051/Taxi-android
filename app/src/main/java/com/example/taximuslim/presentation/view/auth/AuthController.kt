package com.example.taximuslim.presentation.view.auth

import android.os.Bundle
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.controller.BaseController
import com.example.taximuslim.presentation.view.auth.fragments.daughter.StartFragment

class AuthController : BaseController() {
    override fun layoutId() = R.layout.controller_intro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(
            StartFragment.INSTANCE,R.id.container,
            StartFragment.FRAGMENT_ID)
    }

}