package com.example.taximuslim.presentation.view.driver.driverOrderHistory

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.taximuslim.R
import com.example.taximuslim.databinding.DriverOrderHistoryFragmentBinding
import com.example.taximuslim.presentation.view.adapter.OrderHistoryAdapter
import com.example.taximuslim.presentation.view.baseFragment.ObservableFragment
import kotlinx.android.synthetic.main.activity_auth_driver_main.toolbar
import kotlinx.android.synthetic.main.activity_driver_main_screen.*

class DriverOrderHistoryFragment : ObservableFragment() {
    private lateinit var viewModel: DriverOrderHistoryViewModel
    private lateinit var binding: DriverOrderHistoryFragmentBinding
    private val adapter = OrderHistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(DriverOrderHistoryViewModel::class.java)
        binding = DriverOrderHistoryFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.historyRecycler.adapter = adapter
        return binding.root
    }

    override fun setUIState() {
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        (activity as AppCompatActivity).burgerButton.visibility = View.GONE
    }



    override fun setObservers() {
        viewModel.orderHistory.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.fetchOrderHistory()
    }
}
