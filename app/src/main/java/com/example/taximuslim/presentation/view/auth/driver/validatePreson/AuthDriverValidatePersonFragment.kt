package com.example.taximuslim.presentation.view.auth.driver.validatePreson

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.example.taximuslim.R
import com.example.taximuslim.databinding.AuthDriverValidatePersonFragmentBinding
import com.example.taximuslim.domain.models.driver.auth.DriverMainModel
import kotlinx.android.synthetic.main.activity_auth_driver_main.*

class AuthDriverValidatePersonFragment : Fragment() {

    private lateinit var viewModel: AuthDriverValidatePersonViewModel
    private lateinit var driverModel: DriverMainModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        val binding = AuthDriverValidatePersonFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthDriverValidatePersonViewModel::class.java)
        binding.viewModel = viewModel

        driverModel = AuthDriverValidatePersonFragmentArgs.fromBundle(arguments!!)
            .driverModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonText: TextView? = view.findViewById(R.id.main_btn_text) as TextView
        buttonText?.let { mainButtonText ->
            mainButtonText.text = getString(R.string.continuee)
        }
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
