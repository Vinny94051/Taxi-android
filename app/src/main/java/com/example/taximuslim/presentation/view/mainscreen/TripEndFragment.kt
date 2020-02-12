package com.example.taximuslim.presentation.view.mainscreen

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.baseUI.fragment.BaseFragmentCompanion
import com.example.taximuslim.domain.order.models.StatusAndDrivers
import kotlinx.android.synthetic.main.fragment_end_of_trip.*

class TripEndFragment : BaseFragment() {

    companion object : BaseFragmentCompanion() {
        override val ID: String
            get() = "TRIP_END_FRAGMENT"

        override fun newInstance() = TripEndFragment()
    }

    lateinit var statusAndDrivers: StatusAndDrivers
    lateinit var owner: MapsActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = context as MapsActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        closeImageView.setOnClickListener {
            owner.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        pointAAddressTextView.text = statusAndDrivers.startPointAddress
        pointBAddressTextView.text = statusAndDrivers.endPointAddress
        priceTextView.text = statusAndDrivers.price


    }

    override fun layoutId(): Int = R.layout.fragment_end_of_trip
}