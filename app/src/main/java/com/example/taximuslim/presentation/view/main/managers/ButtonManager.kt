package com.example.taximuslim.presentation.view.main.managers

import android.content.Context
import android.widget.Button
import com.example.taximuslim.R
import com.example.taximuslim.presentation.view.main.MapsActivity
import com.example.taximuslim.utils.PriceHolder

class ButtonManager(private val context: Context) {
    companion object {
        private const val ECONOMY_BUTTON_NUMBER = 0
        private const val COMFORT_BUTTON_NUMBER = 1
        private const val BUSINESS_BUTTON_NUMBER = 2
    }

    private var buttonsStates = arrayOf(false, false, false)
    private var comfortOrder: Button =
        (context as MapsActivity).findViewById(R.id.comfort_order)
    private var economyOrder: Button =
        (context as MapsActivity).findViewById(R.id.economy_order)
    private var businessOrder: Button =
        (context as MapsActivity).findViewById(R.id.business_order)


    fun isAtLeastOneBtnActive() = buttonsStates.contains(true)


    fun economyPriceForDialog(){PriceHolder.price = 200}
    fun comfortPriceForDialog(){PriceHolder.price = 500}
    fun businessPriceForDialog(){PriceHolder.price = 1000}

    fun checkEconomyBtnState() {
        if (!checkButtonState(ECONOMY_BUTTON_NUMBER)) {
            setDefaultState(comfortOrder)
            buttonsStates[COMFORT_BUTTON_NUMBER] = false
            setDefaultState(businessOrder)
            buttonsStates[BUSINESS_BUTTON_NUMBER] = false
            setActivateState(economyOrder)
            buttonsStates[ECONOMY_BUTTON_NUMBER] = true
        } else {
            buttonsStates[ECONOMY_BUTTON_NUMBER] = false
            setDefaultState(economyOrder)
        }
    }

    fun checkComfortBtnState() {
        if (!checkButtonState(COMFORT_BUTTON_NUMBER)) {
            setActivateState(comfortOrder)
            buttonsStates[COMFORT_BUTTON_NUMBER] = true
            setDefaultState(businessOrder)
            buttonsStates[BUSINESS_BUTTON_NUMBER] = false
            setDefaultState(economyOrder)
            buttonsStates[ECONOMY_BUTTON_NUMBER] = false

        } else {
            buttonsStates[COMFORT_BUTTON_NUMBER] = false
            setDefaultState(comfortOrder)
        }
    }

    fun checkBusinessBtnState() {
        if (!checkButtonState(BUSINESS_BUTTON_NUMBER)) {
            setDefaultState(comfortOrder)
            buttonsStates[COMFORT_BUTTON_NUMBER] = false
            setActivateState(businessOrder)
            buttonsStates[BUSINESS_BUTTON_NUMBER] = true
            setDefaultState(economyOrder)
            buttonsStates[ECONOMY_BUTTON_NUMBER] = false
        } else {
            buttonsStates[BUSINESS_BUTTON_NUMBER] = false
            setDefaultState(businessOrder)
        }
    }

    private fun setActivateState(button: Button) {
        button.background = context.resources.getDrawable(R.drawable.order_button_back_activate)
        button.setCompoundDrawablesWithIntrinsicBounds(
            0, 0, R.drawable.ic_done_black_24dp,
            0
        )
    }

    private fun setDefaultState(button: Button) {
        button.background = context.resources.getDrawable(R.drawable.order_button_back)

        button.setCompoundDrawablesWithIntrinsicBounds(
            0, 0, 0,
            0
        )
    }

    private fun checkButtonState(arrayNumber: Int) = buttonsStates[arrayNumber]

}


