package com.example.taximuslim.presentation.view.auth.driver.politics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.taximuslim.R
import com.example.taximuslim.databinding.AuthDriverConfidentialityFragmentBinding

class AuthDriverConfidentialityFragment : Fragment() {

    private lateinit var viewModel: AuthDriverConfidentialityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar!!.hide()
        val binding = AuthDriverConfidentialityFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthDriverConfidentialityViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonText: TextView? = view?.findViewById(R.id.main_btn_text) as TextView
        buttonText?.let { mainButtonText ->
            mainButtonText.text = getString(R.string.continuee)
        }
    }


    private fun setObservers() {
        val navController = view!!.findNavController()
        viewModel.navigateToNext.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                viewModel.onNavigateToNext()
            }
        })
        viewModel.navigateToPolitics.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                navController.navigate(R.id.action_driverAuthConfidentialityFragment_to_authDriverPublicOfferFragment)
                viewModel.onNavigateToPolitics()
            }
        })
    }
}
