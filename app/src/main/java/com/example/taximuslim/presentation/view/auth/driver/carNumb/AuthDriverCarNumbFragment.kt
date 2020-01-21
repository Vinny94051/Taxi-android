package com.example.taximuslim.presentation.view.auth.driver.carNumb

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.taximuslim.R

class AuthDriverCarNumbFragment : Fragment() {


    private lateinit var viewModel: AuthDriverCarNumbViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.auth_driver_car_numb_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AuthDriverCarNumbViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
