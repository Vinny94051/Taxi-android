package com.example.taximuslim.presentation.view.driver.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout

import com.example.taximuslim.R
import com.example.taximuslim.databinding.DriverProfileFragmentBinding
import kotlinx.android.synthetic.main.activity_auth_driver_main.*
import kotlinx.android.synthetic.main.activity_auth_driver_main.toolbar
import kotlinx.android.synthetic.main.activity_driver_main_screen.*

class DriverProfileFragment : Fragment() {

        private lateinit var viewModel: DriverProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        viewModel = ViewModelProviders.of(this).get(DriverProfileViewModel::class.java)
        val binding = DriverProfileFragmentBinding.inflate(inflater, container, false)
        binding.viewMdoel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return inflater.inflate(R.layout.driver_profile_fragment, container, false)
    }

}
