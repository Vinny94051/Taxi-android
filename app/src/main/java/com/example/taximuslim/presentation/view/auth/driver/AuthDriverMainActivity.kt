package com.example.taximuslim.presentation.view.auth.driver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.taximuslim.R
import com.example.taximuslim.utils.prefference.saveVerToken
import kotlinx.android.synthetic.main.activity_auth_driver_main.*

class AuthDriverMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_driver_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.arrow_to_left_black)

        saveVerToken(this, "km-QBaLEiR-eX54xG2EdIWpHb9cH_Qwr_1580902334")

        toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        NavigationUI.setupActionBarWithNavController(this, this.findNavController(R.id.nav_host_fragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }


}
