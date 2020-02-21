package com.example.taximuslim.presentation.view.driver.driverMainScreen

import android.annotation.SuppressLint
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
import com.example.taximuslim.App
import com.example.taximuslim.R
import com.example.taximuslim.baseUI.activivty.BaseActivity
import com.example.taximuslim.presentation.view.auth.driver.AuthDriverMainActivity
import com.example.taximuslim.utils.prefference.saveVerToken
import com.example.taximuslim.utils.yandex.IYandexCashBox
import kotlinx.android.synthetic.main.activity_driver_main_screen.*
import kotlinx.android.synthetic.main.nav_header_navigation_drawer.*
import ru.yandex.money.android.sdk.Checkout
import javax.inject.Inject

class DriverMainScreen : BaseActivity() {
    override fun layoutId(): Int = R.layout.activity_driver_main_screen

    init {
        App.appComponent.inject(this)
    }

    private lateinit var viewModel: DriverMainScreenViewModel

    @Inject
    lateinit var yandexCashBox: IYandexCashBox

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IYandexCashBox.REQUEST_CODE_TOKENIZE -> {
                when (resultCode) {
                    RESULT_OK -> {
                        data?.let {_data ->
                            val token = Checkout.createTokenizationResult(_data)
                            viewModel.makePayment(PaymentRequest(
                                token.paymentToken, Amount(payment.toString(), "RUB"),
                                Confirmation("redirect", false, "https://docs.google.com/document/d/1RWyVfBqJDev2-lbYCzwIswI_n1LSvFbxugUQEtacO0Q/edit")
                            , true, "description"
                            ))
                        }
                    }
                    RESULT_CANCELED -> {
                    }
                }
            }
            IYandexCashBox.REQUEST_CODE_3DS -> {

            }
        }
    }
}
