package com.example.taximuslim.presentation.view.auth.driver.car

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
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

import com.example.taximuslim.R
import com.example.taximuslim.databinding.AuthDriverChooseCarFragmentBinding
import kotlinx.android.synthetic.main.auth_driver_choose_car_fragment.*

class AuthDriverChooseCarFragment : Fragment() {

    private lateinit var viewModel: AuthDriverChooseCarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.show()
        val binding = AuthDriverChooseCarFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthDriverChooseCarViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setObservers()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonText: TextView? = view?.findViewById(R.id.main_btn_text) as TextView
        buttonText?.let { mainButtonText ->
            mainButtonText.text = getString(R.string.continuee)
        }
    }

    private fun setObservers(){
        viewModel.navigateTonext.observe(viewLifecycleOwner, Observer {correct ->
            if (correct){
                val navController =view!!.findNavController()
                navController.navigate(R.id.action_authDriverChooseCarFragment_to_authDriverCarNumbFragment)
                viewModel.onNavigate()
            }
        })
    }



}
