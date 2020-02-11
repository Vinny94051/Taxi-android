package com.example.taximuslim.presentation.view.mainscreen

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import com.example.taximuslim.baseUI.fragment.BaseFragmentCompanion
import com.example.taximuslim.domain.order.models.DriverModel
import com.example.taximuslim.presentation.view.adapter.trip.DriversAdapter
import com.example.taximuslim.presentation.viewmodel.maps.TripViewModel
import kotlinx.android.synthetic.main.fragment_choose_driver.*

class ChooseDriverFragment : BaseFragment() {

    companion object : BaseFragmentCompanion() {
        override val ID: String
            get() = "CHOOSE_FRAGMENT_COMPANION"

        override fun newInstance() = ChooseDriverFragment()
    }

    var drivers: List<DriverModel> = mutableListOf()
    var tripId: Int = 0
    private val adapter = DriversAdapter()
    private val viewModel = TripViewModel()


    override fun onResume() {
        super.onResume()
        initList()
        continueButton.setOnClickListener {
            val driverId = adapter.getCurrentDriverId()
            if (tripId != 0 || driverId < 1)
                viewModel.chooseDriver(tripId, driverId)
        }

        viewModel.chooseDriverLiveData.observe(this, Observer { status ->
            if (status.status) {
                mainProgressbar.visibility = View.VISIBLE
            } else showToast("Что-то пошло не так")
        })
    }

    override fun layoutId(): Int = R.layout.fragment_choose_driver

    override fun buttonText(): String = getString(R.string.continuee)

    private fun initList() {
        driverRecyclerList.layoutManager = LinearLayoutManager(context)
        driverRecyclerList.adapter = adapter
        adapter.submitList(drivers)
    }


}