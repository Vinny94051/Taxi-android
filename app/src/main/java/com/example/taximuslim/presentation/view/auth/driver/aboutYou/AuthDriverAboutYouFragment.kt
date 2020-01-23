package com.example.taximuslim.presentation.view.auth.driver.aboutYou

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.example.taximuslim.R
import com.example.taximuslim.databinding.AuthDriverAboutYouFragmentBinding

class AuthDriverAboutYouFragment : Fragment() {

    private lateinit var viewModel: AuthDriverAboutYouViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = AuthDriverAboutYouFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthDriverAboutYouViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setObservers()
    }

    private fun setObservers(){
        viewModel.navigate.observe(viewLifecycleOwner, Observer{navigate ->
            if (navigate){
                val navController = view!!.findNavController()
                navController.navigate(R.id.action_authDriverAboutYouFragment_to_authDriverDocumentsFragment)
                viewModel.onNavigate()
            }
        })
    }
}
