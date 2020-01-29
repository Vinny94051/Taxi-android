package com.example.taximuslim.presentation.view.design.parents

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.Window
import android.view.WindowManager

open class ParentDialog(context: Context) : AlertDialog(context), View.OnClickListener {
    override fun onClick(btn: View?) {}

    var thisWindow: Window? = null

    var closeListener: ((Boolean) -> Unit)? = null
    fun setOnCloseListener(listener: ((Boolean) -> Unit)) {
        closeListener = listener
    }

    fun setLayout() =
        thisWindow?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

    fun initButtons(okBtn: View, cancelBtn: View) {
        okBtn.setOnClickListener(this)
        cancelBtn.setOnClickListener(this)
    }

    fun showKeyboardFrom() {
        thisWindow?.clearFlags(
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
        )
        thisWindow?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }
}