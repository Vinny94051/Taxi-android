package com.example.taximuslim.presentation.view.auth.driver.validatePreson

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.taximuslim.R

class AuthDriverValidatePersonFragment : Fragment() {

    companion object {
        fun newInstance() = AuthDriverValidatePersonFragment()
    }

    private lateinit var viewModel: AuthDriverValidatePersonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.auth_driver_validate_person_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AuthDriverValidatePersonViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
