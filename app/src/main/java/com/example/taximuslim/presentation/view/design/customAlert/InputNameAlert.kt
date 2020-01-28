package com.example.taximuslim.presentation.view.design.customAlert

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import com.example.taximuslim.R
import kotlinx.android.synthetic.main.input_name_alert.*

class InputNameAlert(context: Context, private val listener: (name: String?) -> Unit) : AlertDialog(context){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.input_name_alert)
        setCancelable(false)
        setListeners()
        window?.clearFlags(
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
        )
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }

    private fun setListeners(){
        rejectButton.setOnClickListener {
            dismiss()
        }
        doneButton.setOnClickListener {
            val text = editField.text.toString()
            listener(if (text.isBlank()) null else text)
            dismiss()
        }
    }
}