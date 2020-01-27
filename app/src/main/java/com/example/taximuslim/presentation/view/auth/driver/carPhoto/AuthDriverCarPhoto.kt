package com.example.taximuslim.presentation.view.auth.driver.carPhoto

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController

import com.example.taximuslim.R
import com.example.taximuslim.databinding.AuthDriverCarPhotoFragmentBinding
import kotlinx.android.synthetic.main.activity_auth_driver_main.*

class AuthDriverCarPhoto : Fragment() {

    private lateinit var viewModel: AuthDriverCarPhotoViewModel
    private lateinit var binding: AuthDriverCarPhotoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        binding = AuthDriverCarPhotoFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthDriverCarPhotoViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return  binding.root
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
        setObservers()
    }

    private fun setObservers(){
        viewModel.navigateToNext.observe(viewLifecycleOwner, Observer {navigate ->
            if (navigate) {
                val navController = binding.root.findNavController()
                navController.navigate(R.id.action_authDriverCarPhoto_to_authDriverAboutYouFragment)
                viewModel.onNavigate()
            }
        })
    }
}
