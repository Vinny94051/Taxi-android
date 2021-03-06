package com.example.taximuslim.presentation.view.baseFragment

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
}