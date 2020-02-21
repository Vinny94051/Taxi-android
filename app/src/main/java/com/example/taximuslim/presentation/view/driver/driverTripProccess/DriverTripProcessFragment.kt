package com.example.taximuslim.presentation.view.driver.driverTripProccess

import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.baseUI.fragment.BaseFragmentCompanion

class DriverTripProcessFragment : BaseFragment() {

    companion object : BaseFragmentCompanion() {
        override val ID: String
            get() = "DRIVER_TRIP_PROCESS_FRAGMENT"

        override fun newInstance() = DriverTripProcessFragment()
    }

    override fun layoutId() = R.layout.fragment_driver_side_driver_trip_process
}