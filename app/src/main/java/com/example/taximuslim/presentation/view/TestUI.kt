package com.example.taximuslim.presentation.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.taximuslim.App
import com.example.taximuslim.R
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentRequest
import com.example.taximuslim.data.network.dto.yandex.cashbox.PaymentResponse
import com.example.taximuslim.data.network.dto.yandex.cashbox.SentIdPayRequest
import com.example.taximuslim.presentation.viewmodel.YandexViewModel
import com.example.taximuslim.utils.yandex.IYandexCashBox
import kotlinx.android.synthetic.main.driver_order_fragment.*
import ru.yandex.money.android.sdk.*
import javax.inject.Inject

class TestUI : AppCompatActivity() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var yandexCahsbox: IYandexCashBox
    private val yandexViewModel = YandexViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.driver_order_fragment)
    }

    override fun onResume() {
        super.onResume()
//        createPayBtn.setOnClickListener {
//            createPay()
//        }
        initYandexViewModel()
    }

    private fun createPay() {
        yandexCahsbox.makePayment("Present", "subpresent", 1.00, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IYandexCashBox.REQUEST_CODE_TOKENIZE -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        data?.let {
                            val result = Checkout.createTokenizationResult(it)
                           //TODO result.paymentMethodType
                            Log.e("yandex good", result.paymentToken)
                            yandexViewModel.makePayment(
                                PaymentRequest(
                                    result.paymentToken,
                                    "1.00" //TODO передать сумму платежа
                                )
                            )
                        }
                    }
                    Activity.RESULT_CANCELED -> {
                        Log.e("yandex error", "canceled")
                    }
                }
            }
            IYandexCashBox.REQUEST_CODE_3DS -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        Log.e("yandex 3ds ok", "OK")

                    }
                    Activity.RESULT_CANCELED -> {
                        Log.e("yandex 3ds error", "canceled")
                    }
                }
            }
        }
    }


    private var isFirst = true
    private fun initYandexViewModel() {
        yandexViewModel.paymentLiveData.observe(this, Observer { paymentResponse ->
            when (paymentResponse.status) {
                "pending" -> {
                    yandexViewModel.sentPayId(SentIdPayRequest(paymentResponse.paymentId))
                }
                "waiting_for_capture" -> {
                    Log.e("first status:", "waiting_for_capture")
                }
                "succeeded" -> {
                    Log.e("first status:", "succeeded")
                }
                "canceled" -> {
                    Log.e("first status:", "canceled")
                }
            }
        })

        yandexViewModel.payIdLiveData.observe(this, Observer { paymentResponse: PaymentResponse ->
            if (isFirst) {
                yandexCahsbox.create3DS(paymentResponse.confirmation.url, this)
                isFirst = false
            }
        })

    }

}
