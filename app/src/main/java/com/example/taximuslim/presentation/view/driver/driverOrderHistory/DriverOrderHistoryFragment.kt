package com.example.taximuslim.presentation.view.driver.driverOrderHistory

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.taximuslim.R

class DriverOrderHistoryFragment : Fragment() {
    private lateinit var viewModel: DriverOrderHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(DriverOrderHistoryViewModel::class.java)
        return inflater.inflate(R.layout.driver_order_history_fragment, container, false)
    }
}