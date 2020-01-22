package com.example.taximuslim.presentation.view.auth.driver.carPhoto

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.example.taximuslim.R
import com.example.taximuslim.databinding.AuthDriverCarPhotoFragmentBinding

class AuthDriverCarPhoto : Fragment() {

    companion object {
        fun newInstance() = AuthDriverCarPhoto()
    }

    private lateinit var viewModel: AuthDriverCarPhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = AuthDriverCarPhotoFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthDriverCarPhotoViewModel::class.java)
        binding.viewModel = viewModel
        return  binding.root
    }

    override fun onStart() {
        super.onStart()
        setObservers()
    }

    private fun setObservers(){
        viewModel.navigateToNext.observe(viewLifecycleOwner, Observer {
            //TODO
            viewModel.onNavigate()
        })
    }
}
