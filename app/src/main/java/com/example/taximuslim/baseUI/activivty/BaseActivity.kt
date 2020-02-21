package com.example.taximuslim.baseUI.activivty

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import com.example.taximuslim.baseUI.FragmentRouter

abstract class BaseActivity : AppCompatActivity() {

    protected val router: FragmentRouter
        get() = FragmentRouter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(layoutId())
    }

    @LayoutRes
    abstract fun layoutId(): Int

    fun addFragment(fragment: Fragment, container: Int, tag: String) {
        fragment.enterTransition = Slide(Gravity.END)
        fragment.exitTransition = Slide(Gravity.START)
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(tag)
            .replace(container, fragment, tag)
            .commit()
    }

    fun addFragmentW(fragment: Fragment, container: Int, tag: String) {
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            fragment.enterTransition = Slide(Gravity.END)
            fragment.exitTransition = Slide(Gravity.START)
            supportFragmentManager
                .beginTransaction()
                .replace(container, fragment, tag)
                .commit()
        }
    }

    fun replaceFragment(fragment: Fragment, container: Int, tag: String) {
        fragment.enterTransition = Slide(Gravity.END)
        fragment.exitTransition = Slide(Gravity.START)
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(tag)
            .add(container, fragment, tag)
            .commit()
    }

    fun removeFragment(fragment: Fragment) = supportFragmentManager
        .beginTransaction()
        .remove(fragment)
        .commit()
}