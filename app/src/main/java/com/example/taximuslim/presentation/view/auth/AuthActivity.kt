package com.example.taximuslim.presentation.view.auth

import android.os.Bundle
import android.widget.EditText
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.activivty.BaseActivity
import com.example.taximuslim.presentation.view.auth.fragments.daughter.AuthorizationFragment

class AuthActivity : BaseActivity() {

    companion object {
        private const val COUNTRY_CODE = "+7"
    }


    var userNumber: String? = null
    var isAuth = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addFragment(
            AuthorizationFragment.newInstance(),
            R.id.container,
            AuthorizationFragment.FRAGMENT_ID
        )
    }

    override fun layoutId() = R.layout.controller_intro

    fun saveNumber(editText: EditText) {
        userNumber = (/*COUNTRY_CODE + */  editText.text.toString())
    }

}