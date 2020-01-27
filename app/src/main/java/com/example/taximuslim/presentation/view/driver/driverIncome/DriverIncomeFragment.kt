package com.example.taximuslim.presentation.view.driver.driverIncome

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.taximuslim.R

class DriverIncomeFragment : Fragment() {


    private lateinit var viewModel: DriverIcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(DriverIcomeViewModel::class.java)
        return inflater.inflate(R.layout.driver_income_fragment, container, false)
    }

}
