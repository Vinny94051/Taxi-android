package com.example.taximuslim.presentation.view.auth.driver.rules

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.taximuslim.databinding.DriverAuthRuleFragmentBinding

class DriverAuthRuleFragment : Fragment() {

    private lateinit var viewModel: DriveAuthRuleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar!!.hide()
        val binding = DriverAuthRuleFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(DriveAuthRuleViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

}
