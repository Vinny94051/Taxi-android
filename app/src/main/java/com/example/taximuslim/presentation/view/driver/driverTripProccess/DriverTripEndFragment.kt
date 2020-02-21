package com.example.taximuslim.presentation.view.driver.driverTripProccess

import androidx.fragment.app.Fragment
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.baseUI.fragment.BaseFragmentCompanion

class DriverTripEndFragment : BaseFragment() {

    companion object : BaseFragmentCompanion(){
        override val ID: String
            get() = "DRIVER_TRIP_END_FRAGMENT"

        override fun newInstance() = DriverTripProcessFragment()
    }

    override fun layoutId() = R.layout.fragment_end_of_trip
}