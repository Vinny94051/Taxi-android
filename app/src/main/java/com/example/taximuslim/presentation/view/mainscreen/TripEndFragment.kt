package com.example.taximuslim.presentation.view.mainscreen

import androidx.fragment.app.Fragment
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.baseUI.fragment.BaseFragmentCompanion

class TripEndFragment : BaseFragment() {

    companion object : BaseFragmentCompanion() {
        override val ID: String
            get() = "TRIP_END_FRAGMENT"

        override fun newInstance(): Fragment = TripEndFragment()
    }

    override fun layoutId(): Int = R.layout.fragment_end_of_trip
}