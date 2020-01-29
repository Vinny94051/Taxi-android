package com.example.taximuslim.presentation.view.driver.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.taximuslim.R

class DriverProfileFragment : Fragment() {

    companion object {
        fun newInstance() = DriverProfileFragment()
    }

    private lateinit var viewModel: DriverProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.driver_profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DriverProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
