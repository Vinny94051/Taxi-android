package com.example.taximuslim.presentation.view.driver.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.taximuslim.R
import com.example.taximuslim.databinding.DriverSettingsFragmentBinding
import com.example.taximuslim.presentation.view.baseFragment.ObservableFragment
import com.example.taximuslim.presentation.view.design.customAlert.InputNameAlert
import kotlinx.android.synthetic.main.activity_auth_driver_main.toolbar
import kotlinx.android.synthetic.main.activity_driver_main_screen.*
import java.lang.Exception

class DriverSettingsFragment : ObservableFragment() {

    private lateinit var viewModel: DriverSettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(DriverSettingsViewModel::class.java)
        val binding = DriverSettingsFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewLifecycleOwner.lifecycle.addObserver(viewModel)
        return binding.root
    }

    override fun setObservers() {
        viewModel.changeNameNavigate.observe(viewLifecycleOwner, Observer { navigate ->
            navigate?.let {
                if (navigate) {
                    viewModel.onChangeNameNavigate()
                    InputNameAlert(context!!) { name ->
                        if (name != null) {
                            viewModel.changeName(name)
                        }
                    }.show()

                }
            }
        })
        viewModel.changeNumbNavigate.observe(viewLifecycleOwner, Observer { navigate ->
            navigate?.let {
                if (navigate) {
                    view?.findNavController()
                        ?.navigate(R.id.action_driverSettingsFragment_to_changeNumbFragment)
                    viewModel.onChangeNumbNavigate()
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonText: TextView? = view.findViewById(R.id.main_btn_text) as TextView
        buttonText?.let { mainButtonText ->
            mainButtonText.text = getString(R.string.logout)
        }
    }

    override fun setUIState() {
        try {
            (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
            (activity as AppCompatActivity).supportActionBar?.show()
            (activity as AppCompatActivity).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            (activity as AppCompatActivity).burgerButton.visibility = View.GONE
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
