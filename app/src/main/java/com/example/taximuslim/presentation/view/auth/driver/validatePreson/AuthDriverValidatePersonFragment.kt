package com.example.taximuslim.presentation.view.auth.driver.validatePreson

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.example.taximuslim.R
import com.example.taximuslim.databinding.AuthDriverValidatePersonFragmentBinding

class AuthDriverValidatePersonFragment : Fragment() {

    private lateinit var viewModel: AuthDriverValidatePersonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = AuthDriverValidatePersonFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthDriverValidatePersonViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.navigate.observe(viewLifecycleOwner, Observer{navigate ->
            if (navigate){
                val navController = view!!.findNavController()
                navController.navigate(R.id.action_authDriverValidatePersonFragment_to_driverAuthRuleFragment)
                viewModel.onNavigate()
            }
        })
    }
}
