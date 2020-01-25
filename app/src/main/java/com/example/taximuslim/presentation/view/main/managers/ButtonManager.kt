package com.example.taximuslim.presentation.view.main.managers

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
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
    private var comfortOrder: RelativeLayout =
        (context as MapsActivity).findViewById(R.id.comfort_order)
    private var economyOrder: RelativeLayout =
        (context as MapsActivity).findViewById(R.id.economy_order)
    private var businessOrder: RelativeLayout =
        (context as MapsActivity).findViewById(R.id.business_order)

    private var comforImageView : ImageView =
        (context as MapsActivity).findViewById(R.id.checkMarkImageViewSecond)

    private var ecoImageView : ImageView =
        (context as MapsActivity).findViewById(R.id.checkMarkImageView)

    private var businessImageView : ImageView =
        (context as MapsActivity).findViewById(R.id.checkMarkImageViewThird)

    fun isAtLeastOneBtnActive() = buttonsStates.contains(true)


    fun economyPriceForDialog(){PriceHolder.price = 200}
    fun comfortPriceForDialog(){PriceHolder.price = 500}
    fun businessPriceForDialog(){PriceHolder.price = 1000}

    fun checkEconomyBtnState() {
        if (!checkButtonState(ECONOMY_BUTTON_NUMBER)) {
            setDefaultState(comfortOrder, comforImageView)
            buttonsStates[COMFORT_BUTTON_NUMBER] = false
            setDefaultState(businessOrder, businessImageView)
            buttonsStates[BUSINESS_BUTTON_NUMBER] = false
            setActivateState(economyOrder, ecoImageView)
            buttonsStates[ECONOMY_BUTTON_NUMBER] = true
        } else {
            buttonsStates[ECONOMY_BUTTON_NUMBER] = false
            setDefaultState(economyOrder, ecoImageView)
        }
    }

    fun checkComfortBtnState() {
        if (!checkButtonState(COMFORT_BUTTON_NUMBER)) {
            setActivateState(comfortOrder, comforImageView)
            buttonsStates[COMFORT_BUTTON_NUMBER] = true
            setDefaultState(businessOrder, businessImageView)
            buttonsStates[BUSINESS_BUTTON_NUMBER] = false
            setDefaultState(economyOrder, ecoImageView)
            buttonsStates[ECONOMY_BUTTON_NUMBER] = false

        } else {
            buttonsStates[COMFORT_BUTTON_NUMBER] = false
            setDefaultState(comfortOrder, comforImageView)
        }
    }

    fun checkBusinessBtnState() {
        if (!checkButtonState(BUSINESS_BUTTON_NUMBER)) {
            setDefaultState(comfortOrder, comforImageView)
            buttonsStates[COMFORT_BUTTON_NUMBER] = false
            setActivateState(businessOrder, businessImageView)
            buttonsStates[BUSINESS_BUTTON_NUMBER] = true
            setDefaultState(economyOrder, ecoImageView)
            buttonsStates[ECONOMY_BUTTON_NUMBER] = false
        } else {
            buttonsStates[BUSINESS_BUTTON_NUMBER] = false
            setDefaultState(businessOrder, businessImageView)
        }
    }

    private fun setActivateState(button: RelativeLayout, checkMark : ImageView) {
        button.background = context.resources.getDrawable(R.drawable.order_button_back_activate)
        checkMark.visibility = View.VISIBLE
    }

    private fun setDefaultState(button: RelativeLayout, checkMark : ImageView) {
        button.background = context.resources.getDrawable(R.drawable.order_button_back)
        checkMark.visibility = View.GONE
    }

    private fun checkButtonState(arrayNumber: Int) = buttonsStates[arrayNumber]

}


