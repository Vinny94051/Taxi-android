package com.example.taximuslim.presentation.view.driver.driverMainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import com.example.taximuslim.R
import kotlinx.android.synthetic.main.activity_driver_main_screen.*
import kotlinx.android.synthetic.main.activity_driver_main_screen.toolbar

class DriverMainScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_main_screen)
        val navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(nav_view, navController)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.arrow_to_left_black)
        toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        supportActionBar?.hide()
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }


}
