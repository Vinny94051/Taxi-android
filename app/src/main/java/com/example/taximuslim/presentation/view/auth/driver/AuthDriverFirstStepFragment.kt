package com.example.taximuslim.presentation.view.auth.driver

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

import com.example.taximuslim.R
import com.example.taximuslim.baseUI.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_auth_driver_ferst_step.*


class AuthDriverFirstStepFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_auth_driver_ferst_step, container, false)
    }

    override fun onStart() {
        super.onStart()
        setListeners()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

    private fun setListeners(){
        setOnNextButtonListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonText: TextView? = view.findViewById(R.id.main_btn_text) as TextView
        buttonText?.let { mainButtonText ->
            mainButtonText.text = getString(R.string.start)
        }
    }

    private fun setOnNextButtonListener(){
        mainButton.setOnClickListener{
            Navigation.findNavController(it)
                .navigate(R.id.action_authDriverFirstStepFragment_to_authDriverChooseCarFragment)
        }
    }
}