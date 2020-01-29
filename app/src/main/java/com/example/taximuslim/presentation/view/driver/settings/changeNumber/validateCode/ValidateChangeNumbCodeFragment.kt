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

import com.example.taximuslim.R
import kotlinx.android.synthetic.main.validate_change_numb_code_fragment.*

class ValidateChangeNumbCodeFragment : Fragment() {

    private lateinit var viewModel: ValidateChangeNumbCodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(ValidateChangeNumbCodeViewModel::class.java)
        return inflater.inflate(R.layout.validate_change_numb_code_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonText: TextView? = view.findViewById(R.id.main_btn_text) as TextView
        buttonText?.let { mainButtonText ->
            mainButtonText.text = getString(R.string.start)
        }
        setListeners()
    }

    private fun setListeners(){
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
