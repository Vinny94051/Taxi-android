package com.example.taximuslim.presentation.view.auth.driver.carPhoto

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.taximuslim.R

class AuthDriverCarPhoto : Fragment() {

    companion object {
        fun newInstance() = AuthDriverCarPhoto()
    }

    private lateinit var viewModel: AuthDriverCarPhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.auth_driver_car_photo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AuthDriverCarPhotoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
