package com.example.taximuslim.presentation.view.driver.driverMainScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.auth.driver.AuthDriverMainActivity
import com.example.taximuslim.utils.prefference.saveVerToken
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_driver_main_screen.*
import kotlinx.android.synthetic.main.nav_header_navigation_drawer.*

class DriverMainScreen : AppCompatActivity() {

    private lateinit var viewModel: DriverMainScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_main_screen)
        val navController = this.findNavController(R.id.nav_host_fragment)

        saveVerToken(this,"lZ_uqfkxn8DnOpm6jwbI9XJyJMDKsLZ4_1577428367" )

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.arrow_to_left_black)
        toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        supportActionBar?.hide()

        NavigationUI.setupWithNavController(navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        viewModel = ViewModelProvider(this)[DriverMainScreenViewModel::class.java]
        lifecycle.addObserver(viewModel)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }

    override fun onStart() {
        super.onStart()
        setListeners()
        setObservers()
    }

    @SuppressLint("SetTextI18n")
    private fun setObservers() {
        viewModel.isNewDriver.observe(this, Observer{
            if (it == true){
                finish()
                val intent = Intent(this, AuthDriverMainActivity::class.java)
                startActivity(intent)
            }
        })
        viewModel.profile.observe(this, Observer {
            nav_head_text_head.text = it.driverName
            nav_text_number.text = "+${it.phone}"
        })
    }

    private fun setListeners() {
        burgerButton.setOnClickListener {
            if (!drawerLayout.isDrawerOpen(GravityCompat.START))
                drawerLayout.openDrawer(GravityCompat.START)
            else
                drawerLayout.closeDrawer(GravityCompat.START)
        }
    }


}
