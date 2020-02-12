package com.example.taximuslim.presentation.view.mainscreen

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.baseUI.fragment.BaseFragmentCompanion
import com.example.taximuslim.domain.order.models.StatusAndDrivers
import kotlinx.android.synthetic.main.fragment_driver_on_the_way.*

class DriverOnTheWayFragment : BaseFragment() {

    companion object : BaseFragmentCompanion() {
        override val ID: String
            get() = "TRIP_PROCESS_FRAGMENT"

        override fun newInstance() = DriverOnTheWayFragment()
    }

    lateinit var statusAndDrivers: StatusAndDrivers

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        carTextView.text = "Приедет " + statusAndDrivers.car
        pointAAddressTextView.text = statusAndDrivers.startPointAddress
        pointBAddressTextView.text = statusAndDrivers.endPointAddress
        timeTextView.text = statusAndDrivers.time
        driverStatusTextView.text =
            "Водитель прибудет через " + if (statusAndDrivers.timeToGet == "null") "0  мин" else statusAndDrivers.timeToGet
    }

    override fun layoutId(): Int = R.layout.fragment_driver_on_the_way
}