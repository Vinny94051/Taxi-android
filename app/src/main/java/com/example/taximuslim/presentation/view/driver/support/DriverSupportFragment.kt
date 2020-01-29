package com.example.taximuslim.presentation.view.driver.support

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.taximuslim.R

class DriverSupportFragment : Fragment() {

    private lateinit var viewModel: DraverSupportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(DraverSupportViewModel::class.java)
        return inflater.inflate(R.layout.draver_support_fragment, container, false)
    }

}
