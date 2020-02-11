package com.example.taximuslim.presentation.view.mainscreen

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.baseUI.fragment.BaseFragmentCompanion
import com.example.taximuslim.domain.order.models.StatusAndDrivers
import kotlinx.android.synthetic.main.fragment_driver_wait.*

class DriverWaitFragment : BaseFragment() {

    companion object : BaseFragmentCompanion() {
        override val ID: String
            get() = "DRIVER_WAIT_FRAGMENT"

        override fun newInstance() = DriverWaitFragment()
    }

    lateinit var statusAndDrivers: StatusAndDrivers

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        carTextView.text = "Ожидает " + statusAndDrivers.car
        pointAAddressTextView.text = statusAndDrivers.startPointAddress
        pointBAddressTextView.text = statusAndDrivers.endPointAddress

        makeACallButton.setOnClickListener {makeACall()}
    }

    override fun layoutId(): Int = R.layout.fragment_driver_wait

    private fun makeACall() {

    }
}