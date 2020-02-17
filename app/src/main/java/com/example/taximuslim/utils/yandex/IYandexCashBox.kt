package com.example.taximuslim.utils.yandex

import androidx.appcompat.app.AppCompatActivity

interface IYandexCashBox {

    companion object {
        const val REQUEST_CODE_TOKENIZE = 1004
        const val REQUEST_CODE_3DS = 1005
    }

    /**
     * @param title - main title of payment subject
     * @param subtitle - second title of payment subject
     * @param price - price of the payment subject
     * @param from - from which activity payment will occur
     *
     * Function make payment with yandex cash box services
     *
     */
    fun makePayment(title: String, subtitle: String, price: Double, from: AppCompatActivity)

    /**
     * @param url - 3d secure url
     * @param from - from which activity payment will occur
     *
     * Create 3d secure payment
     */
    fun create3DS(url: String, from: AppCompatActivity)

}