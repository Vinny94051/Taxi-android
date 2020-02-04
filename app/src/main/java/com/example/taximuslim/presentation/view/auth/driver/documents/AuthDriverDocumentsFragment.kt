package com.example.taximuslim.presentation.view.auth.driver.documents

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
import com.example.taximuslim.databinding.AuthDriverCarPhotoFragmentBinding
import com.example.taximuslim.databinding.AuthDriverDocumentsFragmentBinding
import com.example.taximuslim.domain.models.driver.auth.DriverMainModel
import kotlinx.android.synthetic.main.activity_auth_driver_main.*

class AuthDriverDocumentsFragment : Fragment() {

    private lateinit var viewModel: AuthDriverDocumentsViewModel
    private lateinit var driverModel: DriverMainModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        val binding = AuthDriverDocumentsFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthDriverDocumentsViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        driverModel = AuthDriverDocumentsFragmentArgs.fromBundle(arguments!!).driverModel

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
                navController.navigate(AuthDriverDocumentsFragmentDirections
                    .actionAuthDriverDocumentsFragmentToAuthDriverValidatePersonFragment(driverModel))
                viewModel.onNavigate()
            }

        })
    }
}
