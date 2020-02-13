package com.example.taximuslim.baseUI

import android.view.Gravity
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.transition.Slide

class FragmentRouter(var childFragmentManager: FragmentManager) {

    fun addFragment(fragment: Fragment, @IdRes container: Int, id: String) {
        addTransactionSlideAnimation(fragment)
        childFragmentManager
            .beginTransaction()
            .add(container, fragment, id)
            .commit()
    }

    fun replaceFragment(fragment: Fragment, @IdRes container: Int, id: String) {
        addTransactionSlideAnimation(fragment)
        childFragmentManager
            .beginTransaction()
            .replace(container, fragment, id)
            .commit()
    }

    private fun addTransactionSlideAnimation(fragment: Fragment) {
        fragment.enterTransition = Slide(Gravity.END)
        fragment.exitTransition = Slide(Gravity.START)
    }

    fun isFragmentInStack(id: String) =
        when (childFragmentManager.findFragmentByTag(id)) {
            null -> false
            else -> true
        }

    fun removeFragment(fragment: Fragment) =
        childFragmentManager.beginTransaction().remove(fragment).commit()


}