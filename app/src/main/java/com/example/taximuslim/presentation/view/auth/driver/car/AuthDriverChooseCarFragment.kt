package com.example.taximuslim.presentation.view.auth.driver.car

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.example.taximuslim.R
import com.example.taximuslim.databinding.AuthDriverChooseCarFragmentBinding
import com.example.taximuslim.presentation.view.baseFragment.ObservableFragment
import kotlinx.android.synthetic.main.activity_auth_driver_main.*
import kotlinx.android.synthetic.main.auth_driver_choose_car_fragment.*

class AuthDriverChooseCarFragment : ObservableFragment() {

    private lateinit var viewModel: AuthDriverChooseCarViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        val binding = AuthDriverChooseCarFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthDriverChooseCarViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonText: TextView? = view.findViewById(R.id.main_btn_text) as TextView
        buttonText?.let { mainButtonText ->
            mainButtonText.text = getString(R.string.continuee)
        }
    }

    override fun setObservers(){
        viewModel.navigateToNext.observe(viewLifecycleOwner, Observer { correct ->
            if (correct){
                val navController =view!!.findNavController()
                navController.navigate(R.id.action_authDriverChooseCarFragment_to_authDriverCarNumbFragment)
                viewModel.onNavigate()
            }
        })
        viewModel.incorrectData.observe(viewLifecycleOwner, Observer{error ->
            error?.let{error ->
                if (error){
                    Toast.makeText(context, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
                    viewModel.onErrorMessage()
                }
            }
        })
        viewModel.setDefaultValues()
    }

    override fun setListeners() {
        setMarkListener()
        setModelListener()
        setColorListener()
    }

    private fun setColorListener() {
        colorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?,
                position: Int, id: Long
            ) {
                viewModel.setCarColor(position)
            }
        }
    }

    private fun setModelListener() {
        seriesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                        position: Int, id: Long
            ) {
                viewModel.setCarModel(position)
            }
        }
    }

    private fun setMarkListener() {
        markSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?,
                position: Int, id: Long
            ) {
                viewModel.setCarMark(position)
            }
        }

    }
}
