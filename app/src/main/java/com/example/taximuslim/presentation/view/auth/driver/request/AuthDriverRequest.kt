package com.example.taximuslim.presentation.view.auth.driver.request

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.example.taximuslim.R
import com.example.taximuslim.databinding.AuthDriverRequestFragmentBinding
import com.example.taximuslim.presentation.view.baseFragment.ObservableFragment
import com.example.taximuslim.presentation.view.driver.driverMainScreen.DriverMainScreen

class AuthDriverRequest : ObservableFragment() {

    private lateinit var viewModel: AuthDriverRequestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.hide()
        val binding = AuthDriverRequestFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthDriverRequestViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val buttonText: TextView? = view.findViewById(R.id.main_btn_text) as TextView
        buttonText?.let { mainButtonText ->
            mainButtonText.text = getString(R.string.go_to_main_screen)
        }
        super.onViewCreated(view, savedInstanceState)
    }


    override fun setObservers(){
        viewModel.mainNavigation.observe(viewLifecycleOwner, Observer{navigate ->
            if(navigate){
                viewModel.onMainNavigate()
                val intent = Intent(context, DriverMainScreen::class.java)
                startActivity(intent)
                activity!!.finish()
            }
        })
    }
}
