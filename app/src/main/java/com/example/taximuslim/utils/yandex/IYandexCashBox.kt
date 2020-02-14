package com.example.taximuslim.utils.yandex

import androidx.appcompat.app.AppCompatActivity

interface IYandexCashBox {

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
     * Return success code for checking,
     * payment occur successfully
     */
    fun successCode(): Int

}