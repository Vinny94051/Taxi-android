package com.example.taximuslim.presentation.view.auth.driver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation

import com.example.taximuslim.R
import kotlinx.android.synthetic.main.activity_auth_driver_main.*
import kotlinx.android.synthetic.main.fragment_auth_driver_first_step.*


class AuthDriverFirstStepFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        return inflater.inflate(R.layout.fragment_auth_driver_first_step, container, false)
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