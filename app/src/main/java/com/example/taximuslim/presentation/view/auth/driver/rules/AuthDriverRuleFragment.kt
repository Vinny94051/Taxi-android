package com.example.taximuslim.presentation.view.auth.driver.rules

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
import com.example.taximuslim.databinding.AuthDriverRuleFragmentBinding

 class AuthDriverRuleFragment : Fragment() {

    private lateinit var viewModel: AuthDriverRuleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar!!.hide()
        val binding = AuthDriverRuleFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthDriverRuleViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
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
             mainButtonText.text = getString(R.string.next)
         }
     }

    private fun setObservers(){
        viewModel.navigateToNext.observe(viewLifecycleOwner, Observer {navigate ->
            if (navigate){
                val navController = view!!.findNavController()
                navController.navigate(R.id.action_driverAuthRuleFragment_to_driverAuthConfidentialityFragment)
                viewModel.onNavigateToNext()
            }
        })
    }
}
