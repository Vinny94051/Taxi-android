package com.example.taximuslim.presentation.view.driver.driverTripProccess

import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.baseUI.fragment.BaseFragmentCompanion

class DriverWaitFragment : BaseFragment() {

    companion object : BaseFragmentCompanion(){
        override val ID: String
            get() = "DRIVER_WAIT_FRAGMENT_DRIVER"

        override fun newInstance() = DriverWaitFragment()
    }

    override fun layoutId() = R.layout.fragment_driver_side_driver_wait
}