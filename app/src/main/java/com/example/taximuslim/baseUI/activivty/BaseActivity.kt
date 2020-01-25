package com.example.taximuslim.baseUI.activivty

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity : AppCompatActivity(), IBaseActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(layoutId())
    }

    fun replaceFragment(fragment: Fragment, container: Int, tag: String) = supportFragmentManager
        .beginTransaction()
        .addToBackStack(tag)
        .replace(container, fragment, tag)
        .commit()

    fun removeFragment(fragment: Fragment) = supportFragmentManager
        .beginTransaction()
        .remove(fragment)
        .commit()

    fun showToast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}