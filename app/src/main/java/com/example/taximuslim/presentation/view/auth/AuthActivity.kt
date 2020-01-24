package com.example.taximuslim.presentation.view.auth

import android.os.Bundle
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.activivty.BaseActivity
import com.example.taximuslim.presentation.view.auth.fragments.daughter.AuthorizationFragment

class AuthActivity : BaseActivity() {
    override fun layoutId() = R.layout.controller_intro

    var userNumber : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        replaceFragment(
            AuthorizationFragment.INSTANCE,
            R.id.container,
            AuthorizationFragment.FRAGMENT_ID)
    }

}