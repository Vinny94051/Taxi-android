package com.example.taximuslim.baseUI.activivty

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(layoutId())
    }

    @LayoutRes
    abstract fun layoutId() : Int

    fun replaceFragment(fragment: Fragment, container: Int, tag: String) = supportFragmentManager
        .beginTransaction()
        .addToBackStack(tag)
        .replace(container, fragment, tag)
        .commit()

    fun removeFragment(fragment: Fragment) = supportFragmentManager
        .beginTransaction()
        .remove(fragment)
        .commit()
}