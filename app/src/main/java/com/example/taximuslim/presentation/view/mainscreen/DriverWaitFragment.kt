package com.example.taximuslim.presentation.view.mainscreen

import androidx.fragment.app.Fragment
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.baseUI.fragment.BaseFragmentCompanion

class DriverWaitFragment : BaseFragment() {

    companion object : BaseFragmentCompanion() {
        override val ID: String
            get() = "DRIVER_WAIT_FRAGMENT"

        override fun newInstance(): Fragment =
            DriverWaitFragment()
    }

    override fun layoutId(): Int = R.layout.fragment_driver_wait
}