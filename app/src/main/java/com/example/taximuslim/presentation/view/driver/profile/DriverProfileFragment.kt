package com.example.taximuslim.presentation.view.driver.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.example.taximuslim.App

import com.example.taximuslim.R
import com.example.taximuslim.databinding.DriverProfileFragmentBinding
import com.example.taximuslim.presentation.view.baseFragment.ObservableFragment
import com.example.taximuslim.utils.yandex.IYandexCashBox
import kotlinx.android.synthetic.main.activity_auth_driver_main.toolbar
import kotlinx.android.synthetic.main.activity_driver_main_screen.*
import javax.inject.Inject

class DriverProfileFragment : ObservableFragment() {

    init {
        App.appComponent.inject(this)
    }

    private lateinit var viewModel: DriverProfileViewModel

    @Inject
    lateinit var yandexCashBox: IYandexCashBox


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(DriverProfileViewModel::class.java)
        val binding = DriverProfileFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewLifecycleOwner.lifecycle.addObserver(viewModel)
        return binding.root
    }

    override fun setUIState() {
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        (activity as AppCompatActivity).burgerButton.visibility = View.GONE
    }
}
