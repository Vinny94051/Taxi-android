package com.example.taximuslim.presentation.view.driver.driverOrders

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout

import com.example.taximuslim.R
import kotlinx.android.synthetic.main.activity_driver_main_screen.*

class DriverOrderFragment : Fragment() {


    private lateinit var viewModel: DriverOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.hide()
        (activity as AppCompatActivity).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        viewModel = ViewModelProviders.of(this).get(DriverOrderViewModel::class.java)
        return inflater.inflate(R.layout.driver_order_fragment, container, false)
    }

}
