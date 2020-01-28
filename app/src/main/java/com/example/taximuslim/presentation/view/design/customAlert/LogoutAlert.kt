package com.example.taximuslim.presentation.view.design.customAlert

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import com.example.taximuslim.R
import kotlinx.android.synthetic.main.logout_alert.*

class LogoutAlert(context: Context,private val listener: (accept: Boolean) -> Unit) : AlertDialog(context){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logout_alert)
        setCancelable(false)
        setListeners()
    }

    private fun setListeners(){
        acceptButton.setOnClickListener {
            listener(true)
            dismiss()
        }
        rejectButton.setOnClickListener {
            listener(false)
            dismiss()
        }
    }
}