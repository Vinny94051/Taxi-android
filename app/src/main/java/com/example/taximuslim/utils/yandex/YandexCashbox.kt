package com.example.taximuslim.utils.yandex

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.taximuslim.BuildConfig
import ru.yandex.money.android.sdk.*
import java.util.*
import javax.inject.Inject

class YandexCashbox @Inject constructor(var context: Context) : IYandexCashBox {

    companion object {
        private const val REQUEST_CODE_TOKENIZE = 1004
    }

    private val clientApplicationKey: String = BuildConfig.YANDEX_APPLICATION_KEY
    private val shopId: String = BuildConfig.SHOP_ID

    override fun makePayment(title: String, subtitle: String, price: Double, from : AppCompatActivity) {
        val payParameters = PaymentParameters(
            Amount(
                price.toBigDecimal(),
                Currency.getInstance("RUB")
            ),
            title,
            subtitle,
            clientApplicationKey,
            shopId,
            SavePaymentMethod.OFF,
            hashSetOf(PaymentMethodType.BANK_CARD, PaymentMethodType.SBERBANK)
        )

        val intent = Checkout.createTokenizeIntent(context, payParameters)

        from.startActivityForResult(intent, REQUEST_CODE_TOKENIZE)
    }

    override fun successCode(): Int = REQUEST_CODE_TOKENIZE

}