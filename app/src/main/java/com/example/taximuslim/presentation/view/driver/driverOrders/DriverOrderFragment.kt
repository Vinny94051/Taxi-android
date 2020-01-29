package com.example.taximuslim.presentation.view.driver.driverOrders

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.taximuslim.R

class DriverOrderFragment : Fragment() {

    companion object {
        fun newInstance() = DriverOrderFragment()
    }

    private lateinit var viewModel: DriverOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.driver_order_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DriverOrderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
