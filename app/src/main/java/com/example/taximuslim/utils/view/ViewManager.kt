package com.example.taximuslim.utils.view

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

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

    fun showAlert(alert: AlertDialog?) = alert?.show()

    fun setOnFocusListener(editText: EditText, foo: () -> Unit) {
        editText.setOnFocusChangeListener { _, isOpen ->
            if (isOpen)
                foo()
        }
    }
}