package com.example.taximuslim.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.design.customAlert.InputNameAlert
import com.example.taximuslim.presentation.view.design.customAlert.LogoutAlert

class TestUI : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.driver_settings_fragment)
        val alert = InputNameAlert(this){
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
        alert.show()
    }
}
