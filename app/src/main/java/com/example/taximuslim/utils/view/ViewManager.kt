package com.example.taximuslim.utils.view

import android.app.AlertDialog
import android.content.Context
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.taximuslim.presentation.view.clientorder.managers.ButtonManager
import com.example.taximuslim.presentation.view.design.dialogswindow.PriceAlert
import com.example.taximuslim.utils.PriceHolder

class ViewManager(var activity: Context) {

    fun animViewUpToBottomAnim(view: View, translationY: Float, animDuration: Long) {
        view.visibility = View.VISIBLE
        view.animate().translationY(translationY).duration = animDuration
    }

    fun hideKeyBoard(view: View) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
            view.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    fun hideViews(vararg views: View) {
        for (view in views)
            view.visibility = View.GONE
    }

    fun showViews(vararg views: View) {
        for (view in views)
            view.visibility = View.VISIBLE
    }

    fun showPriceAlert(alert: PriceAlert?, buttonManager: ButtonManager, price : Editable) {
        PriceHolder.currentPrice = price
        if(buttonManager.isAtLeastOneBtnActive())
            alert?.show()
    }

    fun showAlert(alert: AlertDialog?) = alert?.show()

    fun removeFocusFromEditTexts(vararg editTexts: EditText){
        for (editText in editTexts){
            editText.isFocusableInTouchMode = false
            editText.isFocusable = false
            editText.isFocusableInTouchMode = true
            editText.isFocusable = true
        }
    }

    fun setOnFocusListener(editText: EditText, foo: () -> Unit) {
        editText.setOnFocusChangeListener { _, isOpen ->
            if (isOpen)
                foo()
        }
    }
}