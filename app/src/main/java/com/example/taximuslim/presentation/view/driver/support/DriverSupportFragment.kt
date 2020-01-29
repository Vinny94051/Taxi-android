package com.example.taximuslim.presentation.view.driver.support

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout

import com.example.taximuslim.R
import com.example.taximuslim.databinding.DriverSupportFragmentBinding
import kotlinx.android.synthetic.main.activity_auth_driver_main.toolbar
import kotlinx.android.synthetic.main.activity_driver_main_screen.*

class DriverSupportFragment : Fragment() {

    private lateinit var viewModel: DriverSupportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        viewModel = ViewModelProviders.of(this).get(DriverSupportViewModel::class.java)

        val binding = DriverSupportFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

}
