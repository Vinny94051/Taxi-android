package com.example.taximuslim.presentation.view.driver.driverOrders

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

import com.example.taximuslim.R
import com.example.taximuslim.databinding.DriverOrderFragmentBinding
import com.example.taximuslim.presentation.view.baseFragment.ObservableFragment
import kotlinx.android.synthetic.main.activity_driver_main_screen.*

class DriverOrderFragment : ObservableFragment() {


    private lateinit var viewModel: DriverOrderViewModel
    private lateinit var binding: DriverOrderFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(DriverOrderViewModel::class.java)
        binding = DriverOrderFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun setUIState() {
        (activity as AppCompatActivity).supportActionBar?.hide()
        (activity as AppCompatActivity).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        (activity as AppCompatActivity).burgerButton.visibility = View.VISIBLE
    }
}
