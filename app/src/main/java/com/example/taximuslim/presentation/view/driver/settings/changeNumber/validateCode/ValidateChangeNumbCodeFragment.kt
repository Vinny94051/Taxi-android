package com.example.taximuslim.presentation.view.driver.settings.changeNumber.validateCode

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.taximuslim.R
import com.example.taximuslim.databinding.ValidateChangeNumbCodeFragmentBinding
import com.example.taximuslim.presentation.view.baseFragment.ObservableFragment
import kotlinx.android.synthetic.main.activity_auth_driver_main.*
import kotlinx.android.synthetic.main.validate_change_numb_code_fragment.*

class ValidateChangeNumbCodeFragment : ObservableFragment() {

    private lateinit var viewModel: ValidateChangeNumbCodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        (activity as AppCompatActivity).supportActionBar?.show()
        viewModel = ViewModelProvider(this).get(ValidateChangeNumbCodeViewModel::class.java)
        val binding =ValidateChangeNumbCodeFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonText: TextView? = view.findViewById(R.id.main_btn_text) as TextView
        buttonText?.let { mainButtonText ->
            mainButtonText.text = getString(R.string.start)
        }
    }

    override fun setObservers() {
        viewModel.navigate.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate == true){
                viewModel.navigate.value = true
                val navController = view!!.findNavController()
                navController.navigate(R.id.action_validateChangeNumbCodeFragment_to_driverSettingsFragment)
            }
        })
    }

    override fun setListeners(){
        setListenersOnEditText(editCode1)
        setListenersOnEditText(editCode2)
        setListenersOnEditText(editCode3)
        setListenersOnEditText(editCode4)
    }

    private fun setListenersOnEditText(view: EditText){
        view.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.isNotEmpty()) {
                    when (view.id) {
                        R.id.editCode1 -> {
                            editCode2.requestFocus()
                        }
                        R.id.editCode2 -> {
                            editCode3.requestFocus()
                        }
                        R.id.editCode3 -> {
                            editCode4.requestFocus()
                        }
                    }
                }
            }

            override fun afterTextChanged(editable: Editable) {
                if (editable.isEmpty()) {
                    when (view.id) {
                        R.id.editCode2 -> {
                            editCode1.requestFocus()
                        }
                        R.id.editCode3 -> {
                            editCode2.requestFocus()
                        }
                        R.id.editCode4 -> {
                            editCode3.requestFocus()
                        }
                    }
                }
            }

            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.isNotEmpty()) {
                    when (view.id) {
                        R.id.editCode1 -> {
                            editCode2.requestFocus()
                        }
                        R.id.editCode2 -> {
                            editCode3.requestFocus()
                        }
                        R.id.editCode3 -> {
                            editCode4.requestFocus()
                        }
                    }
                }
            }
        })

        view.setOnKeyListener { view, keyCode, event ->
            val editText = view as EditText
            if (editText.text.isEmpty()) {
                if ((keyCode == KeyEvent.KEYCODE_DEL)) {
                    when (view.id) {
                        R.id.editCode2 -> {
                            editCode1.requestFocus()
                        }
                        R.id.editCode3 -> {
                            editCode2.requestFocus()
                        }
                        R.id.editCode4 -> {
                            editCode3.requestFocus()
                        }
                    }
                }
                false
            } else {
                when (view.id) {
                    R.id.editCode1 -> {
                        editCode2.requestFocus()
                    }
                    R.id.editCode2 -> {
                        editCode3.requestFocus()
                    }
                    R.id.editCode3 -> {
                        editCode4.requestFocus()
                    }
                }
                false
            }
        }
    }
}
