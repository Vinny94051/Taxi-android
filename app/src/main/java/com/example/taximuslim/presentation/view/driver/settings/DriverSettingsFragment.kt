package com.example.taximuslim.presentation.view.driver.settings

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.taximuslim.R

class DriverSettingsFragment : Fragment() {

    private lateinit var viewModel: DriverSettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(DriverSettingsViewModel::class.java)
        return inflater.inflate(R.layout.driver_settings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonText: TextView? = view.findViewById(R.id.main_btn_text) as TextView
        buttonText?.let { mainButtonText ->
            mainButtonText.text = getString(R.string.logout)
        }
       val builder = AlertDialog.Builder(activity)
    }

}
