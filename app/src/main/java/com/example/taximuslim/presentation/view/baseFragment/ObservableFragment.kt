package com.example.taximuslim.presentation.view.baseFragment

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class ObservableFragment: Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUIState()
        setObservers()
    }

    override fun onStart() {
        super.onStart()
        setListeners()
    }

    open fun setObservers(){}

    open fun setListeners(){}

    open fun setUIState() {}

    fun checkPermission(permission: String): Boolean{
        return context!!.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }
}