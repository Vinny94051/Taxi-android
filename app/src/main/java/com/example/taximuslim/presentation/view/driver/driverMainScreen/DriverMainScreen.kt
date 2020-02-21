package com.example.taximuslim.presentation.view.driver.driverMainScreen

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.activivty.BaseActivity
import com.example.taximuslim.presentation.view.auth.driver.AuthDriverMainActivity
import com.example.taximuslim.utils.prefference.saveVerToken
import kotlinx.android.synthetic.main.activity_driver_main_screen.*
import kotlinx.android.synthetic.main.auth_driver_choose_car_fragment.*

class DriverMainScreen : BaseActivity() {
    override fun layoutId(): Int = R.layout.activity_driver_main_screen

    private lateinit var viewModel: DriverMainScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(nav_view, navController)
        setActionBar()
        NavigationUI.setupActionBarWithNavController(this, navController)
        viewModel = ViewModelProvider(this)[DriverMainScreenViewModel::class.java]
        lifecycle.addObserver(viewModel)
    }

    override fun onStart() {
        super.onStart()
        setListeners()
        setObservers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)


    override fun onSupportNavigateUp(): Boolean =
        this.findNavController(R.id.nav_host_fragment).navigateUp()


    private fun setActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.arrow_to_left_black)
        toolbar.setNavigationIcon(R.drawable.arrow_to_left_black)
        supportActionBar?.hide()
    }

    private fun setObservers() {
        viewModel.isNewDriver.observe(this, Observer {
            if (it == true) {
                finish()
                val intent = Intent(this, AuthDriverMainActivity::class.java)
                startActivity(intent)
            }
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
