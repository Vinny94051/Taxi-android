package com.example.taximuslim.presentation.view.driver.driverOrderHistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taximuslim.R
import kotlinx.android.synthetic.main.activity_driver_order_history.*

class DriverOrderHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_order_history)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolBar.setNavigationIcon(R.drawable.arrow_to_left_black)
    }
}
