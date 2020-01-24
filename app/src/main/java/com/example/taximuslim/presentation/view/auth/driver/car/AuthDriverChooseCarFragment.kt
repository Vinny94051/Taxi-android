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
import kotlinx.android.synthetic.main.auth_driver_choose_car_fragment.*

class AuthDriverChooseCarFragment : Fragment() {

    private lateinit var viewModel: AuthDriverChooseCarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val actionBar = (activity as AppCompatActivity).supportActionBar!!
        actionBar.show()
        return inflater.inflate(R.layout.auth_driver_choose_car_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AuthDriverChooseCarViewModel::class.java)
        setListeners()
        setObservers()
    }

    private fun setListeners(){
        mainButton.setOnClickListener {
            viewModel.checkAndWriteData()
        }
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
