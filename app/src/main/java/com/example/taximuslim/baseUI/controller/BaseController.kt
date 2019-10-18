package com.example.taximuslim.baseUI.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseController : AppCompatActivity(), IBaseController {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(layoutId())
    }

     fun replaceFragment(fragment: Fragment, container: Int, tag: String) = supportFragmentManager
        .beginTransaction()
        .replace(container,fragment,tag)
        .commit()
}