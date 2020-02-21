package com.example.taximuslim.presentation.view.driver.driverTripProccess

import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.baseUI.fragment.BaseFragmentCompanion

class DriverInTheWayFragment : BaseFragment() {

    companion object : BaseFragmentCompanion() {
        override val ID: String
            get() = "DRIVER_IN_THE_WAY_FRAGMENT"

        override fun newInstance() = DriverInTheWayFragment()
    }

    override fun layoutId(): Int = R.layout.fragment_driver_side_driver_in_the_way
}