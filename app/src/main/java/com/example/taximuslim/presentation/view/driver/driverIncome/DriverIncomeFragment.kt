package com.example.taximuslim.presentation.view.driver.driverIncome

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.taximuslim.R
import com.example.taximuslim.databinding.DriverIncomeFragmentBinding

class DriverIncomeFragment : Fragment() {


    private lateinit var viewModel: DriverIncomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(DriverIncomeViewModel::class.java)
        val binding = DriverIncomeFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

}
