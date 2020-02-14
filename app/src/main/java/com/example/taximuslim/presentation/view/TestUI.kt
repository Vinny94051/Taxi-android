package com.example.taximuslim.presentation.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.taximuslim.App
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.design.customAlert.InputNameAlert
import com.example.taximuslim.presentation.view.design.customAlert.LogoutAlert
import com.example.taximuslim.utils.yandex.IYandexCashBox
import kotlinx.android.synthetic.main.driver_order_fragment.*
import ru.yandex.money.android.sdk.*
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

class TestUI : AppCompatActivity() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var yandexCahsbox: IYandexCashBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.driver_order_fragment)
        val alert = InputNameAlert(this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
        alert.show()


    }

    override fun onResume() {
        super.onResume()
        createPayBtn.setOnClickListener {
            createPay()
        }
    }

    private fun createPay() {
        yandexCahsbox.makePayment("Present", "subpresent", 100.0, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == yandexCahsbox.successCode()) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        val result = Checkout.createTokenizationResult(it)
                        Log.e("yandex good", result.paymentToken)
                    }
                }
                Activity.RESULT_CANCELED -> {
                    Log.e("yandex error", "canceled")
                }
            }
        }
    }


}
