package com.example.taximuslim.presentation.view.mainscreen

import androidx.fragment.app.Fragment
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.baseUI.fragment.BaseFragmentCompanion

class DriverOnTheWayFragment : BaseFragment() {

    companion object : BaseFragmentCompanion(){
        override val ID: String
            get() = "TRIP_PROCESS_FRAGMENT"

        override fun newInstance(): Fragment = DriverOnTheWayFragment()
    }

    override fun layoutId(): Int = R.layout.fragment_driver_on_the_way
}